package ru.mironov.marvelapi.service.Impl;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.exception.character.CharacterNotFoundException;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.repository.ComicRepository;
import ru.mironov.marvelapi.service.ComicService;
import ru.mironov.marvelapi.service.ImageService;

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

    @Override
    public Page<ComicDto> getAllComics(Pageable pageable) {
        return new PageImpl<>(comicRepository.findAll(pageable).stream()
                .map(comicMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ComicDto getAndInitializeComic(UUID comicId) {
        ComicDto comicDto = Optional.of(comicId)
                .map(comicRepository::getById)
                .map(comicMapper::toDto)
                .orElseThrow(() -> new CharacterNotFoundException(comicId));

        Hibernate.initialize(comicDto);

        return comicDto;
    }

    @Override
    public Comic localMethodGetComicById(UUID comicId) {
        return comicRepository.getById(comicId);
    }

    @Override
    public ComicInfoDto getComicInfo(UUID comicId) {
        return Optional.of(comicId)
                .map(comicRepository::getById)
                .map(comicMapper::toInfoDto)
                .orElseThrow(() -> new CharacterNotFoundException(comicId));
    }

    @Override
    @Transactional
    public ComicDto createComic(ComicCreateDto comicCreateDto) {
        return Optional.of(comicCreateDto)
                .map(comicMapper::fromCreateDto)
                .map(comicRepository::save)
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public ComicDto updateComic(UUID comicId, ComicUpdateDto comicUpdateDto) {
        Comic comic = localMethodGetComicById(comicId);

        return Optional.ofNullable(comicUpdateDto)
                .map(comicMapper::fromUpdateDto)
                .map(toUpdate -> comicMapper.merge(comic, toUpdate))
                .map(comicRepository::save)
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void deleteComic(UUID comicId) {
        comicRepository.deleteById(comicId);
    }

    @Override
    @Transactional
    public ImageDto uploadImage(UUID comicId, MultipartFile image) {
        ImageDto imageDto = imageService.uploadAndUpdateImage(comicId, image);
        Comic comic = localMethodGetComicById(comicId);
        comic.setImageDownloadUrl(imageDto.getImageDownloadUrl());
        comicRepository.save(comic);

        return imageDto;
    }

    @Override
    @Transactional
    public void deleteImage(UUID comicId, UUID imageId) {
        imageService.deleteImage(imageId);
        localMethodGetComicById(comicId).setImageDownloadUrl("no image");
    }
}
