package andrei.teplyh.repositories;

import andrei.teplyh.entities.UploadedFile;
import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    UploadedFile findUploadedFileByFileNumberAndTemporaryFeedback(Long fileNumber, TemporaryFeedback temporaryFeedback);

    UploadedFile findUploadedFileByFileNumberAndPublishedFeedback(Long fileNumber, PublishedFeedback publishedFeedback);
}
