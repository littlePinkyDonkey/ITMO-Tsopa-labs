package andrei.teplyh.repositories;

import andrei.teplyh.entities.accounts.Account;

public interface LoggerRepository {
    void log(String fileName, Account user);
}
