package andrei.teplyh.services.auth;

import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.exceptions.UserAlreadyExistsException;

public interface RegistrationService {
    User signUp(AccountDto registrationUserDTO) throws UserAlreadyExistsException;
}
