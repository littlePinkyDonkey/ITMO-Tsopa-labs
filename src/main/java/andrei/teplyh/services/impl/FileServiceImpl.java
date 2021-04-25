package andrei.teplyh.services.impl;

import andrei.teplyh.entities.feedbacks.Feedback;
import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import andrei.teplyh.repositories.PublishedFeedbackRepository;
import andrei.teplyh.repositories.TemporaryFeedbackRepository;
import andrei.teplyh.services.FeedbackService;
import andrei.teplyh.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final TemporaryFeedbackRepository temporaryFeedbackRepository;
    private final PublishedFeedbackRepository publishedFeedbackRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public FileServiceImpl(
            TemporaryFeedbackRepository temporaryFeedbackRepository,
            PublishedFeedbackRepository publishedFeedbackRepository
    ) {
        this.temporaryFeedbackRepository = temporaryFeedbackRepository;
        this.publishedFeedbackRepository = publishedFeedbackRepository;
    }

    @Override
    public String saveFile(MultipartFile file) {
        String resultPath = null;
        try {
            if (file != null){
                File directory = new File(uploadPath);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                String uuId = UUID.randomUUID().toString();
                StringBuilder resultFileName = new StringBuilder(uuId)
                        .append(".")
                        .append(file.getOriginalFilename());

                resultPath = uploadPath + "/" + resultFileName;

                file.transferTo(new File(resultPath));
            }

            return resultPath;
        } catch (IOException e) {
            return resultPath;
        }
    }

    @Override
    public InputStreamResource getFileFromTemporaryStorage(Long temporaryReviewId, Long fileNumber) throws FileNotFoundException {
        TemporaryFeedback feedback = temporaryFeedbackRepository.findTemporaryFeedbackByReviewId(temporaryReviewId);

        InputStreamResource resource;
        try {
            resource = new InputStreamResource(new FileInputStream(new File(selectFile(feedback, fileNumber))));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }

        return resource;
    }

    @Override
    public InputStreamResource getFileFromPublishedStorage(Long publishedReviewId, Long fileNumber) throws FileNotFoundException {
        PublishedFeedback feedback = publishedFeedbackRepository.findPublishedFeedbackByReviewId(publishedReviewId);

        InputStreamResource resource;
        try {
            resource = new InputStreamResource(new FileInputStream(new File(selectFile(feedback, fileNumber))));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }

        return resource;
    }

    //TODO: вынеси хранение файла в отдельную блядскую таблицу, чтобы не говнокодить!!!!!!!
    private String selectFile(Feedback feedback, Long fileNumber) throws FileNotFoundException {
        String filePath;

        if (fileNumber == 1 && feedback.getPhoto1Path() != null) {
            filePath = feedback.getPhoto1Path();
        } else if (fileNumber == 2 && feedback.getPhoto2Path() != null) {
            filePath = feedback.getPhoto2Path();
        } else if (fileNumber == 3 && feedback.getPhoto3Path() != null) {
            filePath = feedback.getPhoto3Path();
        } else if (fileNumber == 4 && feedback.getPhoto4Path() != null) {
            filePath = feedback.getPhoto4Path();
        } else {
            throw new FileNotFoundException("No file with such number");
        }

        return filePath;
    }

}
