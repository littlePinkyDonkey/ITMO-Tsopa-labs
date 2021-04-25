package andrei.teplyh.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileService {
    String saveFile(MultipartFile file);

    InputStreamResource getFileFromTemporaryStorage(Long temporaryReviewId, Long fileNumber) throws FileNotFoundException;

    InputStreamResource getFileFromPublishedStorage(Long publishedReviewId, Long fileNumber) throws FileNotFoundException;
}
