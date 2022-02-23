package ru.mironov.marvelapi.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Character;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CharacterService {
    Page<CharacterDto> getAllCharacters(Pageable pageable);

    CharacterDto getAndInitializeCharacter(UUID characterId);

    Character localMethodGetCreatorById(UUID creatorId);

    CharacterInfoDto getCharacterInfo(UUID characterId);

    CharacterDto createCharacter(CharacterCreateDto characterCreateDto);

    CharacterDto updateCharacter(UUID characterId, CharacterUpdateDto characterUpdateDto);

    void deleteCharacter(UUID characterId);

    ImageDto uploadImage(UUID creatorsId, MultipartFile image);

    void deleteImage(UUID creatorId, UUID imageId);
}
