package andrei.teplyh.services;

import andrei.teplyh.entities.accounts.Account;

public interface AccountService {
    Account findAccountByLogin(String login);
}
