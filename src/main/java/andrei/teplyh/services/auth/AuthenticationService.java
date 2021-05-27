package andrei.teplyh.services.auth;

import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.exceptions.UserNotFoundException;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> signIn(AccountDto dto) throws UserNotFoundException;
}
