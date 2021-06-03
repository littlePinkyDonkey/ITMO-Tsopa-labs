package andrei.teplyh.repositories;

import andrei.teplyh.entities.accounts.User;

public interface LoggerRepository {
    void log(String fileName, User user);
}
