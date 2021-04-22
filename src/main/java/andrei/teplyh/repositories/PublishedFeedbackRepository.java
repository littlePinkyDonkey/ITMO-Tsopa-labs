package andrei.teplyh.repositories;

import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishedFeedbackRepository extends JpaRepository<PublishedFeedback, Long> {
    List<PublishedFeedback> findAllByAuthor(User author);
}
