package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.enums.Roles;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Transient
    private Roles role;
}
