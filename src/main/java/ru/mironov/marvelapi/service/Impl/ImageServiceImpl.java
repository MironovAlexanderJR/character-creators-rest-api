package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mironov.marvelapi.domain.entity.Image;
import ru.mironov.marvelapi.domain.exception.image.ImageNotFoundException;
import ru.mironov.marvelapi.domain.exception.image.ImageStorageException;
import ru.mironov.marvelapi.domain.exception.image.ImageTypeException;
import ru.mironov.marvelapi.repository.FileRepository;
import ru.mironov.marvelapi.service.ImageService;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    private final FileRepository fileRepository;

    @Override
    public Image getImage(UUID fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new ImageNotFoundException(fileId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Image uploadAndUpdateImage(UUID authorId, MultipartFile image) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        try {
            if (fileRepository.findByAuthorId(authorId.toString()) != null){
                fileRepository.delete(fileRepository.findByAuthorId(authorId.toString()));
            }

            if (!Objects.equals(image.getContentType(), "image/jpeg")) {
                throw new ImageTypeException();
            }

            Image dbImage =  new Image(fileName, image.getContentType(),
                    image.getSize(), image.getBytes(), authorId.toString());

            fileRepository.save(dbImage);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/")
                    .path(dbImage.getId().toString())
                    .toUriString();

            dbImage.setFileDownloadUri(fileDownloadUri);

            return dbImage;

        } catch (IOException ex) {
            throw new ImageStorageException(fileName);
        }
    }

    @Override
    @Transactional
    public void deleteImage(UUID imageId) {
        fileRepository.deleteById(imageId);
    }
}
