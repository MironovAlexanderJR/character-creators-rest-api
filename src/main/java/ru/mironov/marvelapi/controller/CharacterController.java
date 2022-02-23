package ru.mironov.marvelapi.controller;

import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.service.CharacterService;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/")
    public Page<CharacterDto> getAllCharacters(@PageableDefault(size = 20) Pageable pageable) {
        return characterService.getAllCharacters(pageable);
    }

    @GetMapping("/{characterId}")
    public CharacterDto getCharacter(@PathVariable UUID characterId) {
        return characterService.getAndInitializeCharacter(characterId);
    }

    @GetMapping("/info/{characterId}")
    public CharacterInfoDto getCharacterInfo(@PathVariable UUID characterId) {
        return characterService.getCharacterInfo(characterId);
    }

    @PostMapping
    public CharacterDto createCharacter(@Valid @RequestBody CharacterCreateDto characterCreateDto) {
        return characterService.createCharacter(characterCreateDto);
    }

    @PatchMapping("/{characterId}")
    public CharacterDto updateCharacter(@PathVariable UUID characterId,
                                        @Valid @RequestBody CharacterUpdateDto characterUpdateDto) {
        return characterService.updateCharacter(characterId, characterUpdateDto);
    }

    @DeleteMapping("{characterId}")
    public void deleteCharacter(@PathVariable UUID characterId) {
        characterService.deleteCharacter(characterId);
    }

    @PostMapping("/{characterId}/images")
    public ImageDto uploadImage(@PathVariable UUID characterId, @RequestParam MultipartFile image) {
        return characterService.uploadImage(characterId, image);
    }

    @DeleteMapping("{characterId}/images/{imageId}")
    public void deleteImage(@PathVariable UUID characterId, @PathVariable UUID imageId) {
        characterService.deleteImage(characterId, imageId);
    }
}
