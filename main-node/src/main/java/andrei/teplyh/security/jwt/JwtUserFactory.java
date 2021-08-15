package andrei.teplyh.security.jwt;

import andrei.teplyh.entities.accounts.Account;
import andrei.teplyh.entities.accounts.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public static JwtUser createJwtUser(Account acc) {
        return new JwtUser(
                acc.getAccountId(),
                acc.getPassword(),
                acc.getLogin(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(acc.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRole())
                )
                .collect(Collectors.toList());
    }
}
