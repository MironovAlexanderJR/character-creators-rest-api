package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mironov.marvelapi.domain.entity.Image;
import ru.mironov.marvelapi.service.ImageService;

import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "images")
public class FileController {
    private final ImageService imageService;

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable UUID imageId) {
        Image image = imageService.getImage(imageId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }
}
