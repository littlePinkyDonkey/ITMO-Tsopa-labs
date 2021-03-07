package andrei.teplyh.entities.feedbacks;

import andrei.teplyh.entities.accounts.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity(name = "published_reviews")
public class PublishedFeedback extends Feedback {
    @NotNull
    @Column(name = "PUBLISHED_DATE")
    private Timestamp publishedDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;
}
