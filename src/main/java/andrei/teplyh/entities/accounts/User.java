package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.notifications.UserNotification;
import andrei.teplyh.entities.enums.Roles;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity(name = "users")
public class User extends Account {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotNull
    @Column(name = "USER_ROLE")
    private String userRole;

    @ManyToMany
    @JoinTable(
            name = "notifications_recipients",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MESSAGE_ID")
    )
    private List<UserNotification> userNotifications;

    @OneToMany(mappedBy = "author")
    private List<TemporaryFeedback> temporaryFeedbacks;

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
