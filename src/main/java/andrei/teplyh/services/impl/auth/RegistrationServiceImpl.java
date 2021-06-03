package andrei.teplyh.services.impl.auth;

import andrei.teplyh.annotations.LogXML;
import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.entities.accounts.Account;
import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.exceptions.UserAlreadyExistsException;
import andrei.teplyh.mappers.RegistrationMapper;
import andrei.teplyh.services.AccountService;
import andrei.teplyh.services.UserService;
import andrei.teplyh.services.auth.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final AccountService accountService;
    private final UserService userService;
    private final RegistrationMapper mapper;

    @Autowired
    public RegistrationServiceImpl(
            AccountService accountService,
            UserService userService,
            RegistrationMapper mapper
    ) {
        this.accountService = accountService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @LogXML
    @Override
    @Transactional
    public User signUp(AccountDto accountDto) throws UserAlreadyExistsException {
        Account account = accountService.findAccountByLogin(accountDto.getLogin());
        if (account != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        return userService.registerUser(mapper.dtoToEntity(accountDto));
    }
}
