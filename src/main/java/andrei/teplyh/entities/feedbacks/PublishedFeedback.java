package andrei.teplyh.entities.feedbacks;

import andrei.teplyh.entities.UploadedFile;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "published_reviews")
public class PublishedFeedback extends Feedback {
    @Column(name = "PUBLISHED_DATE", nullable = false)
    private Timestamp publishedDate;

    @OneToMany(mappedBy = "publishedFeedback")
    private List<UploadedFile> files = new ArrayList<>();
}
