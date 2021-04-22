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
public class PublishedFeedbackDto {
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
    private MultipartFile photo2;
    private MultipartFile photo3;
    private MultipartFile photo4;
}
