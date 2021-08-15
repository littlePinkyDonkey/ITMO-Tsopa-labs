package andrei.teplyh.services.impl;

import andrei.teplyh.entities.accounts.Role;
import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.entities.enums.AccountRoles;
import andrei.teplyh.repositories.UserRepository;
import andrei.teplyh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserByAccountId(id);
    }

    @Override
    public User registerUser(User user) {
        Role role = new Role();
        role.setRole(AccountRoles.USER.toString());

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
