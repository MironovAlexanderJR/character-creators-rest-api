package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.entity.Image;
import ru.mironov.marvelapi.domain.exception.comic.ComicNotFoundException;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.domain.mapper.ImageMapper;
import ru.mironov.marvelapi.repository.ComicRepository;
import ru.mironov.marvelapi.service.ComicService;
import ru.mironov.marvelapi.service.ImageService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ComicServiceImpl implements ComicService {
    private final ImageService imageService;
    private final ComicRepository comicRepository;
    private final ComicMapper comicMapper;
    private final ImageMapper imageMapper;

    @Override
    public List<Comic> getAllComic() {
        return comicRepository.findAll();
    }

    @Override
    public Comic getComic(UUID comicId) {
        return comicRepository.findById(comicId)
                .orElseThrow(() -> new ComicNotFoundException(comicId));
    }

    @Override
    @Transactional
    public Comic createComic(Comic comicJson) {
        return comicRepository.save(comicJson);
    }

    @Override
    @Transactional
    public Comic updateComic(UUID comicId, Comic comicJson) {
        return Optional.of(comicId)
                .map(this::getComic)
                .map(current -> comicMapper.merge(current, comicJson))
                .map(comicRepository::save)
                .orElseThrow(() -> new ComicNotFoundException(comicId));
    }

    @Override
    @Transactional
    public void deleteComic(UUID comicId) {
        comicRepository.deleteById(comicId);
    }

    @Override
    @Transactional
    public ImageDto uploadImage(UUID comicId, MultipartFile image) {
        Image uploadImage = imageService.uploadAndUpdateImage(comicId, image);
        Comic comic = getComic(comicId);
        comic.setImageUrl(uploadImage.getFileDownloadUri());
        updateComic(comicId, comic);

        return imageMapper.toDto(uploadImage);
    }

    @Override
    @Transactional
    public void deleteImage(UUID comicId, UUID imageId) {
        imageService.deleteImage(imageId);
        getComic(comicId).setImageUrl("no image");
    }
}
