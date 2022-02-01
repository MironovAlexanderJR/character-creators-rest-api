package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.service.ComicService;

import java.util.Optional;

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

    @GetMapping("/{comicId}")
    public ComicDto getComic(@PathVariable Long comicId) {
        return Optional.of(comicId)
                .map(comicService::getComic)
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{comicId}")
    public ComicInfoDto getCharacterInfo(@PathVariable Long comicId) {
        return Optional.of(comicId)
                .map(comicService::getComic)
                .map(comicMapper::toInfoDto)
                .orElseThrow();
    }

    @PostMapping
    public ComicDto createComic(@RequestBody ComicCreateDto createDto) {
        return Optional.ofNullable(createDto)
                .map(comicMapper::fromCreateDto)
                .map(comicService::createComic)
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{comicId}")
    public ComicDto updateComic(@PathVariable Long comicId, @RequestBody ComicUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(comicMapper::fromUpdateDto)
                .map(toUpdate -> comicService.updateComic(comicId, toUpdate))
                .map(comicMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{comicId}")
    public void deleteComic(@PathVariable Long comicId) {
        comicService.deleteComic(comicId);
    }
}
