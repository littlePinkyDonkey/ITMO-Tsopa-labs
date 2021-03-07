package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.notifications.UserNotification;
import andrei.teplyh.entities.enums.Roles;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity(name = "administrators")
public class Administrator extends Account {
    @Id
    @Column(name = "ADMINISTRATOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long administratorId;

    @NotNull
    @Column(name = "ADMIN_ROLE")
    private String adminRole;

    @OneToMany(mappedBy = "inspector")
    private List<TemporaryFeedback> inspections;

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
