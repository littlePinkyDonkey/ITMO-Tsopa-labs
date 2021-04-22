package andrei.teplyh.mappers;

import andrei.teplyh.dto.TemporaryFeedbackDto;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TemporaryFeedbackMapper {

    public TemporaryFeedback dtoToEntity(TemporaryFeedbackDto dto) {
        TemporaryFeedback entity = new TemporaryFeedback();

        entity.setCarModelAndMark(dto.getCarModelAndMark());
        entity.setCarModification(dto.getCarModification());
        entity.setCarReleaseDate(Date.from(dto.getCarReleaseDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        entity.setReviewBody(dto.getReviewBody());
        entity.setCarAdvantages(dto.getCarAdvantages());
        entity.setCarDisadvantages(dto.getCarDisadvantages());
        entity.setConclusion(dto.getConclusion());
        entity.setOwnership(dto.getOwnership());
        entity.setMileage(dto.getMileage());

        return entity;
    }

    public TemporaryFeedbackDto entityToDto(TemporaryFeedback entity) {
        TemporaryFeedbackDto dto = new TemporaryFeedbackDto();

        dto.setReviewId(entity.getReviewId());
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

    public List<TemporaryFeedbackDto> listEntityToListDto(List<TemporaryFeedback> entities) {
        List<TemporaryFeedbackDto> temporaryFeedbackDtoList = new ArrayList<>();

        for (TemporaryFeedback entity : entities) {
            temporaryFeedbackDtoList.add(entityToDto(entity));
        }

        return temporaryFeedbackDtoList;

    }
}
