package ru.mironov.marvelapi.service;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Image;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
public interface ImageService {
    Image getImage(UUID fileId);

    ImageDto uploadAndUpdateImage(UUID authorId, MultipartFile image);

    void deleteImage(UUID imageId);
}
