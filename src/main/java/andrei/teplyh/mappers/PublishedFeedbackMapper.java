package andrei.teplyh.mappers;

import andrei.teplyh.dto.PublishedFeedbackDto;
import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PublishedFeedbackMapper {
    public PublishedFeedback mapTemporaryFeedbackToPublishedFeedback(TemporaryFeedback temporaryFeedback) {
        PublishedFeedback publishedFeedback = new PublishedFeedback();

        publishedFeedback.setCarModelAndMark(temporaryFeedback.getCarModelAndMark());
        publishedFeedback.setCarModification(temporaryFeedback.getCarModification());
        publishedFeedback.setCarReleaseDate(temporaryFeedback.getCarReleaseDate());
        publishedFeedback.setReviewBody(temporaryFeedback.getReviewBody());
        publishedFeedback.setCarAdvantages(temporaryFeedback.getCarAdvantages());
        publishedFeedback.setCarDisadvantages(temporaryFeedback.getCarDisadvantages());
        publishedFeedback.setConclusion(temporaryFeedback.getConclusion());
        publishedFeedback.setOwnership(temporaryFeedback.getOwnership());
        publishedFeedback.setMileage(temporaryFeedback.getMileage());
        publishedFeedback.setAuthor(temporaryFeedback.getAuthor());
        publishedFeedback.setFiles(temporaryFeedback.getFiles());

        return publishedFeedback;
    }

    public PublishedFeedbackDto entityToDto(PublishedFeedback entity) {
        PublishedFeedbackDto dto = new PublishedFeedbackDto();

        dto.setCarModelAndMark(entity.getCarModelAndMark());
        dto.setCarModification(entity.getCarModification());
        dto.setCarReleaseDate(LocalDate.from(new Date(entity.getCarReleaseDate().getTime()).toInstant().atZone(ZoneId.systemDefault())));
        dto.setReviewBody(entity.getReviewBody());
        dto.setCarAdvantages(entity.getCarAdvantages());
        dto.setCarDisadvantages(entity.getCarDisadvantages());
        dto.setConclusion(entity.getConclusion());
        dto.setOwnership(entity.getOwnership());
        dto.setMileage(entity.getMileage());
        dto.setUserLogin(entity.getAuthor().getLogin());

        return dto;
    }

    public List<PublishedFeedbackDto> listEntityToListDto(List<PublishedFeedback> entities) {
        List<PublishedFeedbackDto> publishedFeedbackDtoList = new ArrayList<>();

        for (PublishedFeedback entity : entities) {
            publishedFeedbackDtoList.add(entityToDto(entity));
        }

        return publishedFeedbackDtoList;
    }

}
