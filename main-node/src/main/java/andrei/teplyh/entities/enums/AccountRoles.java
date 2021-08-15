package andrei.teplyh.entities.enums;

import java.util.stream.Stream;

public enum AccountRoles {
    USER("USER") {
        @Override
        public String toString() {
            return "ROLE_USER";
        }
    },
    BANNED_USER("BANNED_USER") {
        @Override
        public String toString() {
            return "ROLE_BANNED_USER";
        }
    },
    ADMIN("ADMIN") {
        @Override
        public String toString() {
            return "ROLE_ADMIN";
        }
    };

    private String securityRole;

    AccountRoles(String securityRole) {
        this.securityRole = securityRole;
    }

    public String getSecurityRole() {
        return this.securityRole;
    }

    public static AccountRoles of(String value) {
        return Stream.of(AccountRoles.values())
                .filter(role -> role.toString().equals(value))
                .findFirst()
                .orElse(null);
    }
}
