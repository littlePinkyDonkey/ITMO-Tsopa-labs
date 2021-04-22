package andrei.teplyh.services;

import andrei.teplyh.dto.UserNotificationDto;
import andrei.teplyh.entities.notifications.UserNotification;

import java.util.List;

public interface NotificationService {

    boolean generateNotificationToUser(UserNotificationDto dto);

    UserNotificationDto getNotification();

    List<UserNotificationDto> getAllNotifications(Long userId);
}
