package andrei.teplyh.entities.notifications;

import andrei.teplyh.entities.accounts.Administrator;
import andrei.teplyh.entities.accounts.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity(name = "notifications")
public class UserNotification {
    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @NotNull
    @Column(name = "BODY")
    private String notificationBody;

    @NotNull
    @Column(name = "DATE_OF_SENDING")
    private Timestamp dateOfSending;

    @NotNull
    @Column(name = "REVISION_RESULT")
    private String revisionResult;

    /**
     * администратор, отправивший уведомление
     **/
    @NotNull
    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private Administrator sender;

    /**
     * список пользователей, которым нужно отправить уведомление
     **/
    @NotNull
    @ManyToMany
    @JoinTable(
            name = "messages_recipients",
            joinColumns = @JoinColumn(name = "MESSAGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<User> recipients;
}
