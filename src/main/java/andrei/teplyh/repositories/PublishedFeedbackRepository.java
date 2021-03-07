package andrei.teplyh.repositories;

import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishedFeedbackRepository extends JpaRepository<PublishedFeedback, Long> {
}
