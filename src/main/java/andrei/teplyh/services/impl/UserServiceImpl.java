package andrei.teplyh.services.impl;

import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.repositories.UserRepository;
import andrei.teplyh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserByAccountId(id);
    }
}
