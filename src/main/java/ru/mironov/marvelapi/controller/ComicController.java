package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.service.ComicService;

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
@RequestMapping(path = "comics")
public class ComicController {
    private final ComicService comicService;
    private final ComicMapper comicMapper;

    @GetMapping("/")
    public List<ComicDto> getAllComic() {
        return comicService.getAllComic().stream()
                .map(comicMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{comicId}")
    public ComicDto getComic(@PathVariable UUID comicId) {
        return Optional.of(comicId)
                .map(comicService::getComic)
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{comicId}")
    public ComicInfoDto getCharacterInfo(@PathVariable UUID comicId) {
        return Optional.of(comicId)
                .map(comicService::getComic)
                .map(comicMapper::toInfoDto)
                .orElseThrow();
    }

    @PostMapping
    public ComicDto createComic(@Valid @RequestBody ComicCreateDto createDto) {
        return Optional.ofNullable(createDto)
                .map(comicMapper::fromCreateDto)
                .map(comicService::createComic)
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{comicId}")
    public ComicDto updateComic(@PathVariable UUID comicId, @Valid @RequestBody ComicUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(comicMapper::fromUpdateDto)
                .map(toUpdate -> comicService.updateComic(comicId, toUpdate))
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{comicId}")
    public void deleteComic(@PathVariable UUID comicId) {
        comicService.deleteComic(comicId);
    }
}
