package andrei.teplyh.mappers;

import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.entities.accounts.User;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {
    public User dtoToEntity(AccountDto dto) {
        User entity = new User();

        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
