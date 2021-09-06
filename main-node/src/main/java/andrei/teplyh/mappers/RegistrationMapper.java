package andrei.teplyh.mappers;

import andrei.teplyh.dto.AccountDto;
import andrei.teplyh.entities.accounts.User;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper implements Mapper {

    @Override
    public Object dtoToEntity(Object dto) {
        User entity = new User();
        AccountDto accountDto = (AccountDto) dto;

        entity.setLogin(accountDto.getLogin());
        entity.setPassword(accountDto.getPassword());

        return entity;
    }

    @Override
    public Object entityToDto(Object entity) {
        return null;
    }
}
