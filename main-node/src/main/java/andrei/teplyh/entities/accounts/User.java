package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import andrei.teplyh.entities.notifications.UserNotification;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "users")
public class User extends Account {

    /**
     * список уведомлений, пришедших данному пользователю
     **/
    @ManyToMany
    @JoinTable(
            name = "notifications_recipients",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MESSAGE_ID")
    )
    private List<UserNotification> userNotifications;

    /**
     * список всех отзывов, которые ещё не опубликованы
     **/
    @OneToMany(mappedBy = "author")
    private List<TemporaryFeedback> temporaryFeedbacks;

    /**
     * список опубликованных отзывов
     **/
    @OneToMany(mappedBy = "author")
    private List<PublishedFeedback> publishedFeedbacks;
}
