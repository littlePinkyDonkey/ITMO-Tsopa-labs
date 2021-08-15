package andrei.teplyh.services;

import andrei.teplyh.dto.PublishedFeedbackDto;
import andrei.teplyh.dto.TemporaryFeedbackDto;
import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;

import java.util.List;

public interface FeedbackService {
    boolean saveNewTemporaryFeedback(TemporaryFeedbackDto dto) throws Exception;

    boolean savePublishedFeedback(TemporaryFeedback entity);

    void updateTemporaryFeedbackStatus(TemporaryFeedback entity);

    TemporaryFeedback getTemporaryFeedbackById(Long id);

    PublishedFeedback getPublishedFeedbackById(Long id);

    List<TemporaryFeedbackDto> getAllTemporaryFeedbacks(Long administratorId);

    List<PublishedFeedbackDto> getAllPublishedFeedbacks(Long userId);

    boolean updateTemporaryFeedback(TemporaryFeedbackDto dto) throws NullPointerException;
}
