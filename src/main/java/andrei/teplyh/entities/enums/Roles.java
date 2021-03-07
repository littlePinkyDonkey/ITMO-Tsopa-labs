package andrei.teplyh.entities.enums;

import java.util.stream.Stream;

public enum Roles {
    USER() {
        @Override
        public String toString() {
            return "ROLE_USER";
        }
    },
    BANNED_USER() {
        @Override
        public String toString() {
            return "ROLE_BANNED_USER";
        }
    },
    ADMIN() {
        @Override
        public String toString() {
            return "ROLE_ADMIN";
        }
    };

    public static Roles of(String value) {
        return Stream.of(Roles.values())
                .filter(role -> role.toString().equals(value))
                .findFirst()
                .orElse(null);
    }
}
