package andrei.teplyh.repositories;

import andrei.teplyh.entities.accounts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);

    User findUserByAccountId(Long id);
}
