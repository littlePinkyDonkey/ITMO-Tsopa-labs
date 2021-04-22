package andrei.teplyh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryFeedbackDto {
    private long reviewId;
    private String carModelAndMark;
    private String carModification;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate carReleaseDate;
    private String reviewBody;
    private String carAdvantages;
    private String carDisadvantages;
    private String conclusion;
    private String ownership;
    private int mileage;
    private String userLogin;

    private MultipartFile photo1;
    private String photo1Path;

    private MultipartFile photo2;
    private String photo2Path;

    private MultipartFile photo3;
    private String photo3Path;

    private MultipartFile photo4;
    private String photo4Path;
}