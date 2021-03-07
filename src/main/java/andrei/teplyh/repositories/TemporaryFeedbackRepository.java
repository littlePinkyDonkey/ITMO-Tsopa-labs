package andrei.teplyh.repositories;

import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryFeedbackRepository extends JpaRepository<TemporaryFeedback, Long> {
}
