package andrei.teplyh.entities.feedbacks;

import andrei.teplyh.entities.accounts.Administrator;
import andrei.teplyh.entities.enums.FeedbackStatuses;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "temporary_reviews")
public class TemporaryFeedback extends Feedback {
    @NotNull
    @Column(name = "FEEDBACK_STATUS")
    private FeedbackStatuses status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "INSPECTOR_ID")
    private Administrator inspector;
}
