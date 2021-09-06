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
public class PublishedFeedbackMapper implements Mapper {

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

    public List<PublishedFeedbackDto> listEntityToListDto(List<PublishedFeedback> entities) {
        List<PublishedFeedbackDto> publishedFeedbackDtoList = new ArrayList<>();

        for (PublishedFeedback entity : entities) {
            publishedFeedbackDtoList.add((PublishedFeedbackDto) entityToDto(entity));
        }

        return publishedFeedbackDtoList;
    }

    @Override
    public Object dtoToEntity(Object dto) {
        return null;
    }

    @Override
    public Object entityToDto(Object entity) {
        PublishedFeedbackDto dto = new PublishedFeedbackDto();
        PublishedFeedback publishedFeedback = (PublishedFeedback) entity;

        dto.setCarModelAndMark(publishedFeedback.getCarModelAndMark());
        dto.setCarModification(publishedFeedback.getCarModification());
        dto.setCarReleaseDate(LocalDate.from(new Date(publishedFeedback.getCarReleaseDate().getTime()).toInstant().atZone(ZoneId.systemDefault())));
        dto.setReviewBody(publishedFeedback.getReviewBody());
        dto.setCarAdvantages(publishedFeedback.getCarAdvantages());
        dto.setCarDisadvantages(publishedFeedback.getCarDisadvantages());
        dto.setConclusion(publishedFeedback.getConclusion());
        dto.setOwnership(publishedFeedback.getOwnership());
        dto.setMileage(publishedFeedback.getMileage());
        dto.setUserLogin(publishedFeedback.getAuthor().getLogin());
        dto.setFilesCount(publishedFeedback.getFiles().size());

        return dto;
    }
}
