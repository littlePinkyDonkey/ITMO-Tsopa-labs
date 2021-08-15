package andrei.teplyh.security;

import andrei.teplyh.entities.accounts.Account;
import andrei.teplyh.security.jwt.JwtUserFactory;
import andrei.teplyh.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.findAccountByLogin(login);

        if (account == null) {
            throw new UsernameNotFoundException(String.format("User %s not found.", login));
        }

        return JwtUserFactory.createJwtUser(account);
    }
}
