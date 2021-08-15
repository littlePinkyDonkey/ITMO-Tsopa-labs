package andrei.teplyh.entities;

import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "files")
public class UploadedFile {
    @Id
    @Column(name = "FILE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(name = "FILE_NUMBER", nullable = false)
    private Long fileNumber;

    @Column(name = "FILE_PATH", nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "TEMPORARY_REVIEW_ID")
    private TemporaryFeedback temporaryFeedback;

    @ManyToOne
    @JoinColumn(name = "PUBLISHED_REVIEW_ID")
    private PublishedFeedback publishedFeedback;
}
