package ru.mironov.marvelapi.service.Impl;

import java.util.Optional;
import java.util.Set;
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
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorCreateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorInfoDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.entity.Creator;
import ru.mironov.marvelapi.domain.exception.character.CharacterNotFoundException;
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.domain.mapper.CreatorMapper;
import ru.mironov.marvelapi.repository.CreatorRepository;
import ru.mironov.marvelapi.service.CharacterService;
import ru.mironov.marvelapi.service.ComicService;
import ru.mironov.marvelapi.service.CreatorService;
import ru.mironov.marvelapi.service.ImageService;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreatorServiceImpl implements CreatorService {
    private final CharacterService characterService;
    private final ComicService comicService;
    private final ImageService imageService;
    private final CreatorRepository creatorRepository;
    private final CharacterMapper characterMapper;
    private final ComicMapper comicMapper;
    private final CreatorMapper creatorMapper;

    @Override
    public Page<CreatorDto> getAllCreator(Pageable pageable) {
        return new PageImpl<>(creatorRepository.findAll(pageable).stream()
                .map(creatorMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public CreatorDto getAndInitializeCreator(UUID creatorId) {
        CreatorDto creatorDto = Optional.of(creatorId)
                .map(creatorRepository::getById)
                .map(creatorMapper::toDto)
                .orElseThrow(() -> new CharacterNotFoundException(creatorId));

        Hibernate.initialize(creatorDto);

        return creatorDto;
    }

    @Override
    public CreatorInfoDto getCreatorInfo(UUID creatorId) {
        return Optional.of(creatorId)
                .map(creatorRepository::getById)
                .map(creatorMapper::toInfoDto)
                .orElseThrow(() -> new CharacterNotFoundException(creatorId));
    }

    @Override
    public Creator localMethodGetCreatorById(UUID creatorId) {
        return creatorRepository.getById(creatorId);
    }

    @Override
    @Transactional
    public CreatorDto createCreator(CreatorCreateDto creatorCreateDto) {
        return Optional.of(creatorCreateDto)
                .map(creatorMapper::fromCreateDto)
                .map(creatorRepository::save)
                .map(creatorMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public CreatorDto updateCreator(UUID creatorId, CreatorUpdateDto creatorUpdateDto) {
        Creator creator = creatorRepository.findById(creatorId).orElseThrow();
        Set<Character> characterSet = creator.getCharacters();
        Set<Comic> comicSet= creator.getComics();
        Creator changesForCharacter = creatorMapper.fromUpdateDto(creatorUpdateDto);
        Creator modifiedCharacter = creatorMapper.merge(creator, changesForCharacter);
        modifiedCharacter.setCharacters(characterSet);
        modifiedCharacter.setComics(comicSet);
        creatorRepository.save(modifiedCharacter);
        return creatorMapper.toDto(modifiedCharacter);
    }

    @Override
    @Transactional
    public void deleteCreator(UUID creatorId) {
        creatorRepository.deleteById(creatorId);
    }

    @Override
    @Transactional
    public ComicDto assignComic(UUID creatorId, ComicCreateDto comicCreateDto) {
        Comic comic = comicMapper.fromCreateDto(comicCreateDto);
        creatorRepository.getById(creatorId).addComic(comic);
        return comicMapper.toDto(comic);
    }

    @Override
    @Transactional
    public ComicDto updateComic(UUID creatorsId, UUID comicId, ComicUpdateDto comicUpdateDto) {
        return comicService.updateComic(comicId, comicUpdateDto);
    }


    @Override
    @Transactional
    public void deleteComic(UUID creatorsId, UUID comicId) {
        comicService.deleteComic(comicId);
    }

    @Override
    @Transactional
    public CharacterDto assignCharacter(UUID creatorId, CharacterCreateDto characterCreateDto) {
        Character character = characterMapper.fromCreateDto(characterCreateDto);
        creatorRepository.getById(creatorId).addCharacter(character);
        return characterMapper.toDto(character);
    }

    @Override
    @Transactional
    public CharacterDto updateCharacter(UUID creatorsId, UUID characterId, CharacterUpdateDto characterUpdateDto) {
        return characterService.updateCharacter(characterId, characterUpdateDto);
    }


    @Override
    @Transactional
    public void deleteCharacter(UUID creatorsId, UUID characterId) {
        characterService.deleteCharacter(characterId);
    }

    @Override
    @Transactional
    public ImageDto uploadImage(UUID creatorsId, MultipartFile image) {
        ImageDto imageDto = imageService.uploadAndUpdateImage(creatorsId, image);
        Creator creator = localMethodGetCreatorById(creatorsId);
        creator.setImageDownloadUrl(imageDto.getImageDownloadUrl());
        creatorRepository.save(creator);

        return imageDto;
    }

    @Override
    @Transactional
    public void deleteImage(UUID creatorId, UUID imageId) {
        imageService.deleteImage(imageId);
        localMethodGetCreatorById(creatorId).setImageDownloadUrl("no image");
    }
}
