package andrei.teplyh.services.impl.auth;

import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.entities.accounts.Account;
import andrei.teplyh.exceptions.UserNotFoundException;
import andrei.teplyh.security.jwt.util.JwtTokenProvider;
import andrei.teplyh.services.AccountService;
import andrei.teplyh.services.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;

    private final Map<String, String> response = new HashMap<>();

    @Autowired
    public AuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            AccountService accountService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accountService = accountService;
    }

    @Override
    public Map<String, String> signIn(AccountDto dto) {
        String login = dto.getLogin();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, dto.getPassword()));

        Account account = accountService.findAccountByLogin(login);
        if(account == null) {
            throw new UsernameNotFoundException(String.format("No user with login %s", login));
        }

        String token = jwtTokenProvider.createToken(login, account.getRoles(), account.getAccountId());

        response.put("token", token);

        return response;
    }
}
