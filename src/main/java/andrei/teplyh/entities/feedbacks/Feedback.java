package andrei.teplyh.entities.feedbacks;

import andrei.teplyh.entities.accounts.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class Feedback {
    @Id
    @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "MODEL_AND_MARK", nullable = false)
    private String carModelAndMark;

    @Column(name = "MODIFICATIONS")
    private String carModification;

    @Temporal(TemporalType.DATE)
    @Column(name = "CAR_RELEASE_DATE")
    private Date carReleaseDate;

    @Column(name = "REVIEW", length = 1000, nullable = false)
    private String reviewBody;

    @Column(name = "ADVANTAGES", length = 500, nullable = false)
    private String carAdvantages;

    @Column(name = "DISADVANTAGES", length = 500, nullable = false)
    private String carDisadvantages;

    @Column(name = "CONCLUSION", length = 500, nullable = false)
    private String conclusion;

    @Column(name = "OWNERSHIP")
    private String ownership;

    @Column(name = "MILEAGE", nullable = false)
    private int mileage;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;
}
