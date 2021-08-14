package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.notifications.UserNotification;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "administrators")
public class Administrator extends Account {

    @Column(name = "EMAIL", nullable = false)
    private String email;

    /**
     * писок отзывов, которые необходимо просмотреть
     **/
    @OneToMany(mappedBy = "inspector")
    private List<TemporaryFeedback> inspections;

    /**
     * список уведомлений, отправленных пользователю
     **/
    @OneToMany(mappedBy = "sender")
    private List<UserNotification> sendedUserNotifications;
}
