package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.notifications.UserNotification;
import andrei.teplyh.entities.enums.Roles;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "administrators")
public class Administrator extends Account {
    @Column(name = "ADMIN_ROLE", nullable = false)
    private String adminRole;

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

    @PrePersist
    public void prePersist() {
        if (super.getRole() != null) {
            this.adminRole = super.getRole().toString();
        }
    }

    @PostLoad
    public void postLoad() {
        if (adminRole != null) {
            super.setRole(Roles.of(adminRole));
        }
    }
}
