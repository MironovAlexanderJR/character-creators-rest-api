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

    @GetMapping("/{creatorsId}")
    public CreatorDto getCreator(@PathVariable Long creatorsId) {
        return Optional.of(creatorsId)
                .map(creatorService::getCreator)
                .map(creatorMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{creatorsId}")
    public CreatorInfoDto getCreatorInfo(@PathVariable Long creatorsId) {
        return Optional.of(creatorsId)
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

    @PatchMapping("/{creatorsId}")
    public CreatorDto updateCreator(@PathVariable Long creatorsId, @Valid @RequestBody CreatorUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(creatorMapper::fromUpdateDto)
                .map(toUpdate -> creatorService.updateCreator(creatorsId, toUpdate))
                .map(creatorMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{creatorsId}")
    public void deleteCreator(@PathVariable Long creatorsId) {
        creatorService.deleteCreator(creatorsId);
    }

    @PostMapping("/{creatorsId}/characters")
    public CharacterDto assignCharacter(@PathVariable Long creatorsId, @RequestBody CharacterCreateDto characterCreateDto) {
        return characterMapper.toDto(creatorService.assignCharacter(creatorsId, characterMapper.fromCreateDto(characterCreateDto)));
    }

    @PatchMapping("/{creatorsId}/characters/{characterId}")
    public CharacterDto updateCharacter(@PathVariable Long creatorsId, @PathVariable Long characterId,
                                        @RequestBody CharacterUpdateDto characterUpdateDto) {
        return characterMapper.toDto(creatorService.updateCharacter(creatorsId, characterId, characterMapper.fromUpdateDto(characterUpdateDto)));
    }

    @DeleteMapping("/{creatorsId}/characters/{characterId}")
    public void deleteCharacter(@PathVariable Long creatorsId, @PathVariable Long characterId) {
        creatorService.deleteCharacter(creatorsId, characterId);
    }

    @PostMapping("/{creatorsId}/comics")
    public ComicDto assignComic(@PathVariable Long creatorsId, @RequestBody ComicCreateDto comicCreateDto) {
        return comicMapper.toDto(creatorService.assignComic(creatorsId, comicMapper.fromCreateDto(comicCreateDto)));
    }

    @PatchMapping("/{creatorsId}/comics/{comicId}")
    public ComicDto updateComic(@PathVariable Long creatorsId, @PathVariable Long comicId,
                                @RequestBody ComicUpdateDto comicUpdateDto) {
        return comicMapper.toDto(creatorService.updateComic(creatorsId, comicId, comicMapper.fromUpdateDto(comicUpdateDto)));
    }

    @DeleteMapping("/{creatorsId}/comics/{comicId}")
    public void deleteComic(@PathVariable Long creatorsId, @PathVariable Long comicId) {
        creatorService.deleteComic(creatorsId, comicId);
    }
}
