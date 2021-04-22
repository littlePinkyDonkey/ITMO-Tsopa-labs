package andrei.teplyh.controllers;

import andrei.teplyh.dto.TemporaryFeedbackDto;
import andrei.teplyh.exceptions.NoPermossionsException;
import andrei.teplyh.exceptions.UserNotFoundException;
import andrei.teplyh.services.FeedbackService;
import andrei.teplyh.services.FileUploadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(path = "/temporary")
public class TemporaryFeedbackController {
    private final FeedbackService feedbackService;
    private final FileUploadingService fileUploadingService;

    @Autowired
    public TemporaryFeedbackController(FeedbackService feedbackService, FileUploadingService fileUploadingService) {
        this.feedbackService = feedbackService;
        this.fileUploadingService = fileUploadingService;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity createFeedback(@ModelAttribute TemporaryFeedbackDto dto) {
        try {
            String photo1Path = fileUploadingService.saveFile(dto.getPhoto1());
            File file = new File(photo1Path);
            dto.getPhoto1().transferTo(file);
            dto.setPhoto1Path(photo1Path);
            feedbackService.saveNewTemporaryFeedback(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            if (e.getClass() == UserNotFoundException.class) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } else if (e.getClass() == NoPermossionsException.class) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity getTemporaryFeedbacks(@RequestParam(name = "id") Long administratorId) {
        List<TemporaryFeedbackDto> temporaryFeedbackDtoList =
                feedbackService.getAllTemporaryFeedbacks(administratorId);

        if (temporaryFeedbackDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("You don't have any feedback to check");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(temporaryFeedbackDtoList);
        }
    }
}
