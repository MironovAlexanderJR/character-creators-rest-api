package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.service.CharacterService;

import java.util.Optional;

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

    @GetMapping("/{characterId}")
    public CharacterDto getCharacter(@PathVariable Long characterId) {
        return Optional.of(characterId)
                .map(characterService::getCharacter)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{characterId}")
    public CharacterInfoDto getCharacterInfo(@PathVariable Long characterId) {
        return Optional.of(characterId)
                .map(characterService::getCharacter)
                .map(characterMapper::toInfoDto)
                .orElseThrow();
    }

    @PostMapping
    public CharacterDto createCharacter(@RequestBody CharacterCreateDto createDto) {
        return Optional.ofNullable(createDto)
                .map(characterMapper::fromCreateDto)
                .map(characterService::createCharacter)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{characterId}")
    public CharacterDto updateCharacter(@PathVariable Long characterId, @RequestBody CharacterUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(characterMapper::fromUpdateDto)
                .map(toUpdate -> characterService.updateCharacter(characterId, toUpdate))
                .map(characterMapper::toDto)
                .orElseThrow();
    }
}
