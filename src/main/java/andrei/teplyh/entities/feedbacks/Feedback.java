package andrei.teplyh.entities.feedbacks;

import andrei.teplyh.entities.accounts.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@MappedSuperclass
public class Feedback {
    @Id
    @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @NotNull
    @Column(name = "MODEL_AND_MARK")
    private String carModelAndMark;

    @Column(name = "MODIFICATIONS")
    private String carModification;

    @Temporal(TemporalType.DATE)
    @Column(name = "CAR_RELEASE_DATE")
    private Date carReleaseDate;

    @NotNull
    @Column(name = "REVIEW", length = 1000)
    private String reviewBody;

    @NotNull
    @Column(name = "ADVANTAGES", length = 500)
    private String carAdvantages;

    @NotNull
    @Column(name = "DISADVANTAGES", length = 500)
    private String carDisadvantages;

    @NotNull
    @Column(name = "CONCLUSION", length = 500)
    private String conclusion;

    @Column(name = "OWNERSHIP")
    private String ownership;

    @NotNull
    @Column(name = "MILEAGE")
    private int mileage;

    @Column(name = "PHOTO_1_PATH")
    private String photo1Path;

    @Column(name = "PHOTO_2_PATH")
    private String photo2Path;

    @Column(name = "PHOTO_3_PATH")
    private String photo3Path;

    @Column(name = "PHOTO_4_PATH")
    private String photo4Path;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;
}
