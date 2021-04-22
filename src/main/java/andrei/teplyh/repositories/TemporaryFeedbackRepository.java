package andrei.teplyh.repositories;

import andrei.teplyh.entities.accounts.Administrator;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporaryFeedbackRepository extends JpaRepository<TemporaryFeedback, Long> {
    TemporaryFeedback findTemporaryFeedbackByReviewId(long id);

    List<TemporaryFeedback> findAllByInspector(Administrator author);
}
