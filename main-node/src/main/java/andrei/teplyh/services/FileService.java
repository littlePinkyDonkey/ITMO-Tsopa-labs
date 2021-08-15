package andrei.teplyh.services;

import andrei.teplyh.entities.UploadedFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileService {
    List<UploadedFile> saveFiles(List<MultipartFile> file);

    InputStreamResource getFileFromTemporaryStorage(Long temporaryReviewId, Long fileNumber) throws FileNotFoundException;

    InputStreamResource getFileFromPublishedStorage(Long publishedReviewId, Long fileNumber) throws FileNotFoundException;
}
