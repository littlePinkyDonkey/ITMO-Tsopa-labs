package andrei.teplyh.services.impl;

import andrei.teplyh.dto.UserNotificationDto;
import andrei.teplyh.entities.accounts.Administrator;
import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.entities.enums.FeedbackStatuses;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import andrei.teplyh.entities.notifications.UserNotification;
import andrei.teplyh.mappers.NotificationMapper;
import andrei.teplyh.repositories.UserNotificationRepository;
import andrei.teplyh.services.AdministratorService;
import andrei.teplyh.services.FeedbackService;
import andrei.teplyh.services.NotificationService;
import andrei.teplyh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final FeedbackService feedbackService;
    private final AdministratorService administratorService;
    private final UserService userService;

    private final NotificationMapper notificationMapper;

    private final UserNotificationRepository userNotificationRepository;

    @Autowired
    public NotificationServiceImpl(
            FeedbackService feedbackService,
            AdministratorService administratorService,
            UserService userService,
            NotificationMapper notificationMapper,
            UserNotificationRepository userNotificationRepository
    ) {
        this.feedbackService = feedbackService;
        this.userService = userService;
        this.notificationMapper = notificationMapper;
        this.userNotificationRepository = userNotificationRepository;
        this.administratorService = administratorService;
    }

    @Override
    public boolean generateNotificationToUser(UserNotificationDto dto) {
        UserNotification userNotification = notificationMapper.dtoToEntity(dto);

        Administrator administrator = administratorService.findAdministratorByLogin(dto.getSender());
        userNotification.setSender(administrator);

        TemporaryFeedback feedback = feedbackService.getTemporaryFeedbackById(dto.getReviewId());
        User author = feedback.getAuthor();
        author.getUserNotifications().add(userNotification);

        userNotificationRepository.save(userNotification);

        updateFeedbackInfo(feedback, dto.getRevisionResult());

        return true;
    }

    @Override
    public UserNotificationDto getNotification() {
        return null;
    }

    @Override
    public List<UserNotificationDto> getAllNotifications(Long userId) {
        List<UserNotification> notificationList = userService.findUserById(userId).getUserNotifications();

        return notificationMapper.entityListToDtoList(notificationList);
    }

    private void updateFeedbackInfo(TemporaryFeedback feedback, String revisionResult) {
        if (revisionResult.equals("accepted")) {
            feedback.setFeedbackStatus(FeedbackStatuses.ACCEPTED);
            feedbackService.savePublishedFeedback(feedback);
        } else if (revisionResult.equals("rejected")) {
            feedback.setFeedbackStatus(FeedbackStatuses.REJECTED);
            feedbackService.updateTemporaryFeedback(feedback);
        } else {
            feedback.setFeedbackStatus(FeedbackStatuses.EDITS_NEEDED);
            feedbackService.updateTemporaryFeedback(feedback);
        }
    }
}
