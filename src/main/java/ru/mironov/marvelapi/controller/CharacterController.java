package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.service.CharacterService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "characters")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @GetMapping("/")
    public List<CharacterDto> getAllCharacters() {
        return characterService.getAllCharacters().stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{characterId}")
    public CharacterDto getCharacter(@PathVariable UUID characterId) {
        return Optional.of(characterId)
                .map(characterService::getCharacter)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{characterId}")
    public CharacterInfoDto getCharacterInfo(@PathVariable UUID characterId) {
        return Optional.of(characterId)
                .map(characterService::getCharacter)
                .map(characterMapper::toInfoDto)
                .orElseThrow();
    }

    @PostMapping
    public CharacterDto createCharacter(@Valid @RequestBody CharacterCreateDto createDto) {
        return Optional.ofNullable(createDto)
                .map(characterMapper::fromCreateDto)
                .map(characterService::createCharacter)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{characterId}")
    public CharacterDto updateCharacter(@PathVariable UUID characterId, @Valid @RequestBody CharacterUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(characterMapper::fromUpdateDto)
                .map(toUpdate -> characterService.updateCharacter(characterId, toUpdate))
                .map(characterMapper::toDto)
                .orElseThrow();
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
