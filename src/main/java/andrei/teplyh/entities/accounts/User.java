package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.notifications.UserNotification;
import andrei.teplyh.entities.enums.Roles;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "users")
public class User extends Account {
    @Column(name = "USER_ROLE", nullable = false)
    private String userRole;

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

    @PrePersist
    public void prePersist() {
        if (super.getRole() != null) {
            this.userRole = super.getRole().toString();
        }
    }

    @PostLoad
    public void postLoad() {
        if (userRole != null) {
            super.setRole(Roles.of(userRole));
        }
    }
}
