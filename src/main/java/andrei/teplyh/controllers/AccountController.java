package andrei.teplyh.controllers;

import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.exceptions.UserAlreadyExistsException;
import andrei.teplyh.exceptions.UserNotFoundException;
import andrei.teplyh.services.auth.AuthenticationService;
import andrei.teplyh.services.auth.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {
    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AccountController(
            RegistrationService registrationService,
            AuthenticationService authenticationService
    ) {
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/signUp", produces = "application/json")
    public ResponseEntity register(@RequestBody AccountDto dto) {
        try {
            registrationService.signUp(dto);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(path = "/signIn", produces = "application/json")
    public ResponseEntity auth(@RequestBody AccountDto dto) {
        try {
            Map<String, String> response = authenticationService.signIn(dto);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
