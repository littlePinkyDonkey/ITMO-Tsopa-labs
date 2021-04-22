package andrei.teplyh.repositories;

import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.entities.notifications.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
}
