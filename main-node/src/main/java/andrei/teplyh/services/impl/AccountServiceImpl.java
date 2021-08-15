package andrei.teplyh.services.impl;

import andrei.teplyh.entities.accounts.Account;
import andrei.teplyh.repositories.AccountRepository;
import andrei.teplyh.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findAccountByLogin(String login) {
        return accountRepository.findAccountByLogin(login);
    }
}
