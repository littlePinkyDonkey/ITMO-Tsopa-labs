package andrei.teplyh.services.auth;

import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.exceptions.UserAlreadyExistsException;

public interface RegistrationService {
    void signUp(AccountDto registrationUserDTO) throws UserAlreadyExistsException;
}
