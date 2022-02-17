package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.domain.mapper.CreatorMapper;
import ru.mironov.marvelapi.service.CreatorService;

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
@RequestMapping(path = "creators")
public class CreatorController {
    private final CreatorService creatorService;
    private final CreatorMapper creatorMapper;
    private final ComicMapper comicMapper;
    private final CharacterMapper characterMapper;

    @GetMapping("/")
    public List<CreatorDto> getAllCreator() {
        return creatorService.getAllCreator().stream()
                .map(creatorMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{creatorId}")
    public CreatorDto getCreator(@PathVariable UUID creatorId) {
        return Optional.of(creatorId)
                .map(creatorService::getCreator)
                .map(creatorMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{creatorId}")
    public CreatorInfoDto getCreatorInfo(@PathVariable UUID creatorId) {
        return Optional.of(creatorId)
                .map(creatorService::getCreator)
                .map(creatorMapper::toInfoDto)
                .orElseThrow();
    }

    @PostMapping
    public CreatorDto createCreator(@Valid @RequestBody CreatorCreateDto createDto) {
        return Optional.ofNullable(createDto)
                .map(creatorMapper::fromCreateDto)
                .map(creatorService::createCreator)
                .map(creatorMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{creatorId}")
    public CreatorDto updateCreator(@PathVariable UUID creatorId, @Valid @RequestBody CreatorUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(creatorMapper::fromUpdateDto)
                .map(toUpdate -> creatorService.updateCreator(creatorId, toUpdate))
                .map(creatorMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{creatorId}")
    public void deleteCreator(@PathVariable UUID creatorId) {
        creatorService.deleteCreator(creatorId);
    }

    @PostMapping("/{creatorId}/characters")
    public CharacterDto assignCharacter(@PathVariable UUID creatorId, @RequestBody CharacterCreateDto characterCreateDto) {
        return characterMapper.toDto(creatorService.assignCharacter(creatorId, characterMapper.fromCreateDto(characterCreateDto)));
    }

    @PatchMapping("/{creatorId}/characters/{characterId}")
    public CharacterDto updateCharacter(@PathVariable UUID creatorId, @PathVariable UUID characterId,
                                        @RequestBody CharacterUpdateDto characterUpdateDto) {
        return characterMapper.toDto(creatorService.updateCharacter(creatorId, characterId, characterMapper.fromUpdateDto(characterUpdateDto)));
    }

    @DeleteMapping("/{creatorId}/characters/{characterId}")
    public void deleteCharacter(@PathVariable UUID creatorId, @PathVariable UUID characterId) {
        creatorService.deleteCharacter(creatorId, characterId);
    }

    @PostMapping("/{creatorId}/comics")
    public ComicDto assignComic(@PathVariable UUID creatorId, @RequestBody ComicCreateDto comicCreateDto) {
        return comicMapper.toDto(creatorService.assignComic(creatorId, comicMapper.fromCreateDto(comicCreateDto)));
    }

    @PatchMapping("/{creatorId}/comics/{comicId}")
    public ComicDto updateComic(@PathVariable UUID creatorId, @PathVariable UUID comicId,
                                @RequestBody ComicUpdateDto comicUpdateDto) {
        return comicMapper.toDto(creatorService.updateComic(creatorId, comicId, comicMapper.fromUpdateDto(comicUpdateDto)));
    }

    @DeleteMapping("/{creatorId}/comics/{comicId}")
    public void deleteComic(@PathVariable UUID creatorId, @PathVariable UUID comicId) {
        creatorService.deleteComic(creatorId, comicId);
    }
}