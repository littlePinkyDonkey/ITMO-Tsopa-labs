package andrei.teplyh.entities.feedbacks;

import andrei.teplyh.entities.UploadedFile;
import andrei.teplyh.entities.accounts.Administrator;
import andrei.teplyh.entities.enums.FeedbackStatuses;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "temporary_reviews")
public class TemporaryFeedback extends Feedback {
    @Column(name = "FEEDBACK_STATUS", nullable = false)
    private String status;

    @Transient
    private FeedbackStatuses feedbackStatus;

    @ManyToOne
    @JoinColumn(name = "INSPECTOR_ID", nullable = false)
    private Administrator inspector;

    @OneToMany(mappedBy = "temporaryFeedback")
    private List<UploadedFile> files = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (feedbackStatus != null) {
            status = feedbackStatus.toString();
        }
    }

    @PostLoad
    public void postLoad() {
        if (status != null) {
            feedbackStatus = FeedbackStatuses.of(status);
        }
    }
}
