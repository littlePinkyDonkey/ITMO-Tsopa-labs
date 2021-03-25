package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.enums.Roles;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @NotNull
    @Column(name = "LOGIN", unique = true)
    private String login;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private Roles role;
}
