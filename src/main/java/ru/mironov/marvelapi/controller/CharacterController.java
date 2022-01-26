package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "characters")
public class CharacterController {

    @GetMapping("/{characterId}")
    public CharacterDto getCharacter(@PathVariable Long characterId) {
        return null;
    }

    @GetMapping("/info/{characterId}")
    public CharacterInfoDto getCharacterInfo(@PathVariable Long characterId) {
        return null;
    }

    @PostMapping
    public CharacterDto createCharacter(@RequestBody CharacterCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{characterId}")
    public CharacterDto updateCharacter(@PathVariable Long characterId, @RequestBody CharacterUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("{characterId}")
    public void deleteCharacter(@PathVariable Long characterId) {

    }

    @GetMapping("/{characterId}/comics/{comicId}")
    public ComicDto getComic(@PathVariable Long characterId, @PathVariable Long comicId) {
        return null;
    }

    @PostMapping("/{characterId}/comics")
    public ComicDto assignComic(@PathVariable Long characterId, @RequestBody ComicCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{characterId}/comics/{comicId}")
    public ComicDto updateComic(@PathVariable Long characterId, @PathVariable Long comicId,
                                @RequestBody ComicUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("/{characterId}/comics/{comicId}")
    public void deleteComic(@PathVariable Long characterId, @PathVariable Long comicId) {

    }
}
