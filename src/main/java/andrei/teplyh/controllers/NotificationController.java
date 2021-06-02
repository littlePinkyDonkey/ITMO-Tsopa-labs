package andrei.teplyh.controllers;

import andrei.teplyh.dto.UserNotificationDto;
import andrei.teplyh.services.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/notification")
@Api(tags = {"notifications"}, description = "Управление пользовательскими уведомлениями")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(path = "/send", produces = "application/json")
    @ApiOperation(value = "Отправить уведомление пользователю", authorizations = @Authorization("ADMIN"))
    public ResponseEntity sendNotification(@RequestBody UserNotificationDto dto) {
        notificationService.generateNotificationToUser(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/all", produces = "application/json")
    @ApiOperation(value = "Получить все пришедшие уведомления",
            authorizations = {@Authorization("BANNED USER"), @Authorization("USER")})
    public ResponseEntity getAllUserNotifications(@RequestParam(name = "id") Long userId) {
        List<UserNotificationDto> notifications = notificationService.getAllNotifications(userId);

        if (notifications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(notifications);
        }

    }
}
