package andrei.teplyh.services.impl;

import andrei.teplyh.services.FileUploadingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadingServiceImpl implements FileUploadingService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String saveFile(MultipartFile file) {
        String resultPath = null;
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
        }

        return resultPath;
    }
}
