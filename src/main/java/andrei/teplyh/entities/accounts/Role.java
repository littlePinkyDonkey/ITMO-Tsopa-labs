package andrei.teplyh.entities.accounts;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "roles")
public class Role {
    @Id
    @Column(name = "ROLE_VALUE")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = @JoinColumn(name = "ROLE_VALUE"),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID")
    )
    private List<Account> accounts = new ArrayList<>();
}
