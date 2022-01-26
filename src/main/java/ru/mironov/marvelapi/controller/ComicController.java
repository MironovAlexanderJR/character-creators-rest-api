package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "comics")
public class ComicController {
    @GetMapping("/{comicId}")
    public ComicDto getComic(@PathVariable Long comicId) {
        return null;
    }

    @GetMapping("/info/{comicId}")
    public ComicInfoDto getCharacterInfo(@PathVariable Long comicId) {
        return null;
    }

    @PostMapping
    public ComicDto createComic(@RequestBody ComicCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{comicId}")
    public ComicDto updateComic(@PathVariable Long comicId, @RequestBody ComicUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("{comicId}")
    public void deleteComic(@PathVariable Long comicId) {

    }

    @GetMapping("/{comicId}/characters/{characterId}")
    public CharacterDto getCharacter(@PathVariable Long comicId, @PathVariable Long characterId) {
        return null;
    }

    @PostMapping("/{comicId}/characters")
    public CharacterDto assignCharacter(@PathVariable Long comicId, @RequestBody CharacterCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{comicId}/characters/{characterId}")
    public CharacterDto updateCharacter(@PathVariable Long comicId, @PathVariable Long characterId,
                                @RequestBody CharacterUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("/{comicId}/characters/{characterId}")
    public void deleteComic(@PathVariable Long comicId, @PathVariable Long characterId) {

    }
}
