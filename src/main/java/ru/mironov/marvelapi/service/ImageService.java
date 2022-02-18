package ru.mironov.marvelapi.service;

import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.entity.Image;

import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
public interface ImageService {
    Image getImage(UUID fileId);

    Image uploadAndUpdateImage(UUID authorId, MultipartFile image);

    void deleteImage(UUID imageId);
}
