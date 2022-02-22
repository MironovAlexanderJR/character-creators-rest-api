package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Image;
import ru.mironov.marvelapi.domain.exception.character.CharacterNotFoundException;
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.domain.mapper.ImageMapper;
import ru.mironov.marvelapi.repository.CharacterRepository;
import ru.mironov.marvelapi.service.CharacterService;
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
public class CharacterServiceImpl implements CharacterService {
    private final ImageService imageService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ImageMapper imageMapper;

    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character getCharacter(UUID characterId) {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new CharacterNotFoundException(characterId));
    }

    @Override
    @Transactional
    public Character createCharacter(Character characterJson) {
        return characterRepository.save(characterJson);
    }

    @Override
    @Transactional
    public Character updateCharacter(UUID characterId, Character characterJson) {
        return Optional.of(characterId)
                .map(this::getCharacter)
                .map(current -> characterMapper.merge(current, characterJson))
                .map(characterRepository::save)
                .orElseThrow(() -> new CharacterNotFoundException(characterId));
    }

    @Override
    @Transactional
    public void deleteCharacter(UUID characterId) {
        characterRepository.deleteById(characterId);
    }

    @Override
    @Transactional
    public ImageDto uploadImage(UUID characterId, MultipartFile image) {
        Image uploadImage = imageService.uploadAndUpdateImage(characterId, image);
        Character character = getCharacter(characterId);
        character.setImageUrl(uploadImage.getFileDownloadUri());
        characterRepository.save(character);

        return imageMapper.toDto(uploadImage);
    }

    @Override
    @Transactional
    public void deleteImage(UUID characterId, UUID imageId) {
        imageService.deleteImage(imageId);
        getCharacter(characterId).setImageUrl("no image");
    }
}
