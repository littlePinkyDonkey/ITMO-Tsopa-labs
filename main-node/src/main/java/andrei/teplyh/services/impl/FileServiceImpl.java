package andrei.teplyh.services.impl;

import andrei.teplyh.entities.UploadedFile;
import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import andrei.teplyh.repositories.PublishedFeedbackRepository;
import andrei.teplyh.repositories.TemporaryFeedbackRepository;
import andrei.teplyh.repositories.UploadedFileRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final TemporaryFeedbackRepository temporaryFeedbackRepository;
    private final PublishedFeedbackRepository publishedFeedbackRepository;
    private final UploadedFileRepository uploadedFileRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public FileServiceImpl(
            TemporaryFeedbackRepository temporaryFeedbackRepository,
            PublishedFeedbackRepository publishedFeedbackRepository,
            UploadedFileRepository uploadedFileRepository
    ) {
        this.temporaryFeedbackRepository = temporaryFeedbackRepository;
        this.publishedFeedbackRepository = publishedFeedbackRepository;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public List<UploadedFile> saveFiles(List<MultipartFile> files) {
        List<UploadedFile> uploadedFiles = new ArrayList<>();
        try {
            if (files != null && !files.isEmpty()) {

                long fileNumber = 1;
                for(MultipartFile file : files) {
                    File directory = new File(uploadPath);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }

                    String uuId = UUID.randomUUID().toString();
                    StringBuilder resultFileName = new StringBuilder(uuId)
                            .append(".")
                            .append(file.getOriginalFilename());

                    String resultPath = uploadPath + "/" + resultFileName;

                    file.transferTo(new File(resultPath));

                    UploadedFile uploadedFile = new UploadedFile();
                    uploadedFile.setFileNumber(fileNumber++);
                    uploadedFile.setFilePath(resultPath);

                    uploadedFiles.add(uploadedFile);
                }
            }

            return uploadedFiles;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public InputStreamResource getFileFromTemporaryStorage(Long temporaryReviewId, Long fileNumber) throws FileNotFoundException {
        TemporaryFeedback feedback = temporaryFeedbackRepository.findTemporaryFeedbackByReviewId(temporaryReviewId);
        if (feedback == null) {
            throw new NullPointerException("No such feedback. Maybe your feedback is already published");
        }

        UploadedFile uploadedFile =
                uploadedFileRepository.findUploadedFileByFileNumberAndTemporaryFeedback(fileNumber, feedback);

        InputStreamResource resource;
        try {
            resource = new InputStreamResource(new FileInputStream(new File(uploadedFile.getFilePath())));
        } catch (FileNotFoundException | NullPointerException e) {
            throw new FileNotFoundException("File not found");
        }

        return resource;
    }

    @Override
    public InputStreamResource getFileFromPublishedStorage(Long publishedReviewId, Long fileNumber) throws FileNotFoundException {
        PublishedFeedback feedback = publishedFeedbackRepository.findPublishedFeedbackByReviewId(publishedReviewId);
        if (feedback == null) {
            throw new NullPointerException("No such feedback. Maybe your feedback still not published");
        }

        UploadedFile uploadedFile =
                uploadedFileRepository.findUploadedFileByFileNumberAndPublishedFeedback(fileNumber, feedback);

        InputStreamResource resource;
        try {
            resource = new InputStreamResource(new FileInputStream(new File(uploadedFile.getFilePath())));
        } catch (FileNotFoundException | NullPointerException e) {
            throw new FileNotFoundException("File not found");
        }

        return resource;
    }
}
