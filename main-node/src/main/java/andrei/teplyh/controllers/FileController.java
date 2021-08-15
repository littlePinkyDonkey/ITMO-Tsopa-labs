package andrei.teplyh.controllers;

import andrei.teplyh.services.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(path = "/api/file")
@Api(tags = {"files"}, description = "Управление прикреплёнными файлами")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(path = "/temporary")
    @ApiOperation(value = "Получить файл из неопубликованного отзыва",
            authorizations = {@Authorization("ADMIN"), @Authorization("USER")})
    public ResponseEntity getTemporaryFile(@RequestParam(name = "reviewId") Long reviewId,
                                           @RequestParam(name = "fileNumber") Long fileNumber) {
        try {
            InputStreamResource resource = fileService.getFileFromTemporaryStorage(reviewId, fileNumber);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (FileNotFoundException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        }
    }

    @GetMapping(path = "/published")
    @ApiOperation(value = "Получить файл из опубликованного отзыва")
    public ResponseEntity getPublishedFile(@RequestParam(name = "reviewId") Long reviewId,
                                           @RequestParam(name = "fileNumber") Long fileNumber) {
        try {
            InputStreamResource resource = fileService.getFileFromPublishedStorage(reviewId, fileNumber);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (FileNotFoundException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        }
    }
}
