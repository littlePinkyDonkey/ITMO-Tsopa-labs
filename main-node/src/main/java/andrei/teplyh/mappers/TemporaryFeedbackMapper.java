package andrei.teplyh.mappers;

import andrei.teplyh.dto.TemporaryFeedbackDto;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TemporaryFeedbackMapper implements Mapper {

    public List<TemporaryFeedbackDto> listEntityToListDto(List<TemporaryFeedback> entities) {
        List<TemporaryFeedbackDto> temporaryFeedbackDtoList = new ArrayList<>();

        for (TemporaryFeedback entity : entities) {
            temporaryFeedbackDtoList.add((TemporaryFeedbackDto) entityToDto(entity));
        }

        return temporaryFeedbackDtoList;
    }

    public void updateEntity(TemporaryFeedback entity, TemporaryFeedbackDto dto) {
        entity.setCarModelAndMark(dto.getCarModelAndMark());
        entity.setCarModification(dto.getCarModification());
        entity.setCarReleaseDate(Date.from(dto.getCarReleaseDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        entity.setReviewBody(dto.getReviewBody());
        entity.setCarAdvantages(dto.getCarAdvantages());
        entity.setCarDisadvantages(dto.getCarDisadvantages());
        entity.setConclusion(dto.getConclusion());
        entity.setOwnership(dto.getOwnership());
        entity.setMileage(dto.getMileage());
    }

    @Override
    public Object dtoToEntity(Object dto) {
        TemporaryFeedback entity = new TemporaryFeedback();
        TemporaryFeedbackDto temporaryFeedbackDto = (TemporaryFeedbackDto) dto;

        entity.setCarModelAndMark(temporaryFeedbackDto.getCarModelAndMark());
        entity.setCarModification(temporaryFeedbackDto.getCarModification());
        entity.setCarReleaseDate(Date.from(temporaryFeedbackDto.getCarReleaseDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        entity.setReviewBody(temporaryFeedbackDto.getReviewBody());
        entity.setCarAdvantages(temporaryFeedbackDto.getCarAdvantages());
        entity.setCarDisadvantages(temporaryFeedbackDto.getCarDisadvantages());
        entity.setConclusion(temporaryFeedbackDto.getConclusion());
        entity.setOwnership(temporaryFeedbackDto.getOwnership());
        entity.setMileage(temporaryFeedbackDto.getMileage());

        return entity;
    }

    @Override
    public Object entityToDto(Object entity) {
        TemporaryFeedbackDto dto = new TemporaryFeedbackDto();
        TemporaryFeedback temporaryFeedback = (TemporaryFeedback) entity;

        dto.setReviewId(temporaryFeedback.getReviewId());
        dto.setCarModelAndMark(temporaryFeedback.getCarModelAndMark());
        dto.setCarModification(temporaryFeedback.getCarModification());
        dto.setCarReleaseDate(LocalDate.from(new Date(temporaryFeedback.getCarReleaseDate().getTime()).toInstant().atZone(ZoneId.systemDefault())));
        dto.setReviewBody(temporaryFeedback.getReviewBody());
        dto.setCarAdvantages(temporaryFeedback.getCarAdvantages());
        dto.setCarDisadvantages(temporaryFeedback.getCarDisadvantages());
        dto.setConclusion(temporaryFeedback.getConclusion());
        dto.setOwnership(temporaryFeedback.getOwnership());
        dto.setMileage(temporaryFeedback.getMileage());
        dto.setUserLogin(temporaryFeedback.getAuthor().getLogin());
        dto.setFilesCount(temporaryFeedback.getFiles().size());

        return dto;
    }
}
