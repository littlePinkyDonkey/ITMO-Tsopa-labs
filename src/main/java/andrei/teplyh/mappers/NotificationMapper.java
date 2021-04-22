package andrei.teplyh.mappers;

import andrei.teplyh.dto.UserNotificationDto;
import andrei.teplyh.entities.notifications.UserNotification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapper {

    public UserNotification dtoToEntity(UserNotificationDto dto) {
        UserNotification entity = new UserNotification();

        entity.setNotificationBody(dto.getNotificationBody());
        entity.setDateOfSending(dto.getDateOfSending());
        entity.setRevisionResult(dto.getRevisionResult());

        return entity;
    }

    public UserNotificationDto entityToDto(UserNotification entity) {
        UserNotificationDto dto = new UserNotificationDto();

        dto.setNotificationBody(entity.getNotificationBody());
        dto.setDateOfSending(entity.getDateOfSending());
        dto.setRevisionResult(entity.getRevisionResult());
        dto.setSender(entity.getSender().getLogin());

        return dto;
    }

    public List<UserNotificationDto> entityListToDtoList(List<UserNotification> entities) {
        List<UserNotificationDto> dtoList = new ArrayList<>();

        for (UserNotification entity : entities) {
            dtoList.add(entityToDto(entity));
        }

        return dtoList;
    }
}
