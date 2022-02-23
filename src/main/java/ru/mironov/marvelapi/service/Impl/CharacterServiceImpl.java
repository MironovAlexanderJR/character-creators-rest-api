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
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.exception.character.CharacterNotFoundException;
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.repository.CharacterRepository;
import ru.mironov.marvelapi.service.CharacterService;
import ru.mironov.marvelapi.service.ImageService;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterServiceImpl implements CharacterService {
    private final ImageService imageService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public Page<CharacterDto> getAllCharacters(Pageable pageable) {
        return new PageImpl<>(characterRepository.findAll(pageable).stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public CharacterDto getAndInitializeCharacter(UUID characterId) {
        CharacterDto characterDto = Optional.of(characterId)
                .map(characterRepository::getById)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new CharacterNotFoundException(characterId));

        Hibernate.initialize(characterDto);

        return characterDto;
    }

    @Override
    public Character localMethodGetCreatorById(UUID creatorId) {
        return characterRepository.getById(creatorId);
    }

    @Override
    public CharacterInfoDto getCharacterInfo(UUID characterId) {
        return Optional.of(characterId)
                .map(characterRepository::getById)
                .map(characterMapper::toInfoDto)
                .orElseThrow(() -> new CharacterNotFoundException(characterId));
    }

    @Override
    @Transactional
    public CharacterDto createCharacter(CharacterCreateDto characterCreateDto) {
        return Optional.of(characterCreateDto)
                .map(characterMapper::fromCreateDto)
                .map(characterRepository::save)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public CharacterDto updateCharacter(UUID characterId, CharacterUpdateDto characterUpdateDto) {
        Character character = localMethodGetCreatorById(characterId);

        return Optional.ofNullable(characterUpdateDto)
                .map(characterMapper::fromUpdateDto)
                .map(toUpdate -> characterMapper.merge(character, toUpdate))
                .map(characterRepository::save)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void deleteCharacter(UUID characterId) {
        characterRepository.deleteById(characterId);
    }

    @Override
    @Transactional
    public ImageDto uploadImage(UUID characterId, MultipartFile image) {
        ImageDto imageDto = imageService.uploadAndUpdateImage(characterId, image);
        Character character = localMethodGetCreatorById(characterId);
        character.setImageDownloadUrl(imageDto.getImageDownloadUrl());
        characterRepository.save(character);

        return imageDto;
    }

    @Override
    @Transactional
    public void deleteImage(UUID characterId, UUID imageId) {
        imageService.deleteImage(imageId);
        localMethodGetCreatorById(characterId).setImageDownloadUrl("no image");
    }
}
