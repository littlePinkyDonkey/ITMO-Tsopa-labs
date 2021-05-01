package andrei.teplyh.controllers;

import andrei.teplyh.dto.TemporaryFeedbackDto;
import andrei.teplyh.exceptions.NoPermossionsException;
import andrei.teplyh.exceptions.UserNotFoundException;
import andrei.teplyh.services.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/temporary")
@Api(tags = {"temporary"}, description = "Управление неопубликованными отзывами")
public class TemporaryFeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public TemporaryFeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @ApiOperation("Написать новый отзыв")
    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity createFeedback(@ModelAttribute TemporaryFeedbackDto dto) {
        try {
            feedbackService.saveNewTemporaryFeedback(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            if (e.getClass() == UserNotFoundException.class) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            } else if (e.getClass() == NoPermossionsException.class) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @ApiOperation("Получить все отзывы, ожидающие проверки")
    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity getTemporaryFeedbacks(@RequestParam(name = "id") Long administratorId) {
        List<TemporaryFeedbackDto> temporaryFeedbackDtoList =
                feedbackService.getAllTemporaryFeedbacks(administratorId);

        if (temporaryFeedbackDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(temporaryFeedbackDtoList);
        }
    }

    @ApiOperation("обновить содержание отзыва")
    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity updateTemporaryFeedback(@ModelAttribute TemporaryFeedbackDto dto) {
        try {
            feedbackService.updateTemporaryFeedback(dto);
            return ResponseEntity.ok().build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
