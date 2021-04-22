package andrei.teplyh.controllers;

import andrei.teplyh.dto.PublishedFeedbackDto;
import andrei.teplyh.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/published")
public class PublishedFeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public PublishedFeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity getPublishedFeedbacks(@RequestParam(name = "id") Long userId) {
        List<PublishedFeedbackDto> publishedFeedbackDtoList = feedbackService.getAllPublishedFeedbacks(userId);

        if (publishedFeedbackDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("You don't have any published feedback");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(publishedFeedbackDtoList);
        }
    }
}
