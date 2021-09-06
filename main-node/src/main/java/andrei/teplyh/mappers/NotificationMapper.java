package andrei.teplyh.mappers;

import andrei.teplyh.dto.UserNotificationDto;
import andrei.teplyh.entities.notifications.UserNotification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapper implements Mapper {

    public List<UserNotificationDto> entityListToDtoList(List<UserNotification> entities) {
        List<UserNotificationDto> dtoList = new ArrayList<>();

        for (UserNotification entity : entities) {
            dtoList.add((UserNotificationDto) entityToDto(entity));
        }

        return dtoList;
    }

    @Override
    public Object dtoToEntity(Object dto) {
        UserNotification entity = new UserNotification();
        UserNotificationDto userNotificationDto = (UserNotificationDto) dto;

        entity.setNotificationBody(userNotificationDto.getNotificationBody());
        entity.setDateOfSending(userNotificationDto.getDateOfSending());
        entity.setRevisionResult(userNotificationDto.getRevisionResult());

        return entity;
    }

    @Override
    public Object entityToDto(Object entity) {
        UserNotificationDto dto = new UserNotificationDto();
        UserNotification userNotification = (UserNotification) entity;

        dto.setNotificationBody(userNotification.getNotificationBody());
        dto.setDateOfSending(userNotification.getDateOfSending());
        dto.setRevisionResult(userNotification.getRevisionResult());
        dto.setSender(userNotification.getSender().getLogin());

        return dto;
    }
}
