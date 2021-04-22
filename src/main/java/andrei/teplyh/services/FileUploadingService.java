package andrei.teplyh.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadingService {
    String saveFile(MultipartFile file);
}
