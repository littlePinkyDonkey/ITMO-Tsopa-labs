package andrei.teplyh.entities.accounts;

import andrei.teplyh.entities.enums.Roles;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class Account {
    @NotNull
    @Column(name = "LOGIN", unique = true)
    private String login;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private Roles role;
}
