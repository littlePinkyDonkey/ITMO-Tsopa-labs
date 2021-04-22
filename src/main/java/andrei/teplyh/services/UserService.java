package andrei.teplyh.services;

import andrei.teplyh.entities.accounts.User;

public interface UserService {
    User findUserByLogin(String login);

    User findUserById(Long id);
}
