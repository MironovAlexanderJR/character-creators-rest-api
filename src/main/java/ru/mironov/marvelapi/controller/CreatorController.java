package ru.mironov.marvelapi.controller;

import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorCreateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorInfoDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.service.CreatorService;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "creators")
public class CreatorController {
    private final CreatorService creatorService;

    @GetMapping("/")
    public Page<CreatorDto> getAllCreator(Pageable pageable) {
        return creatorService.getAllCreator(pageable);
    }

    @GetMapping("/{creatorId}")
    public CreatorDto getCreator(@PathVariable UUID creatorId) {
        return creatorService.getAndInitializeCreator(creatorId);
    }

    @GetMapping("/info/{creatorId}")
    public CreatorInfoDto getCreatorInfo(@PathVariable UUID creatorId) {
        return creatorService.getCreatorInfo(creatorId);
    }

    @PostMapping
    public CreatorDto createCreator(@Valid @RequestBody CreatorCreateDto createDto) {
        return creatorService.createCreator(createDto);
    }

    @PatchMapping("/{creatorId}")
    public CreatorDto updateCreator(@PathVariable UUID creatorId,
                                    @Valid @RequestBody CreatorUpdateDto creatorUpdateDto) {
        return creatorService.updateCreator(creatorId, creatorUpdateDto);
    }

    @DeleteMapping("{creatorId}")
    public void deleteCreator(@PathVariable UUID creatorId) {
        creatorService.deleteCreator(creatorId);
    }

    @PostMapping("/{creatorId}/characters")
    public CharacterDto assignCharacter(@PathVariable UUID creatorId,
                                        @RequestBody CharacterCreateDto characterCreateDto) {
        return creatorService.assignCharacter(creatorId, characterCreateDto);
    }

    @PatchMapping("/{creatorId}/characters/{characterId}")
    public CharacterDto updateCharacter(@PathVariable UUID creatorId, @PathVariable UUID characterId,
                                        @RequestBody CharacterUpdateDto characterUpdateDto) {
        return creatorService.updateCharacter(creatorId, characterId, characterUpdateDto);
    }

    @DeleteMapping("/{creatorId}/characters/{characterId}")
    public void deleteCharacter(@PathVariable UUID creatorId, @PathVariable UUID characterId) {
        creatorService.deleteCharacter(creatorId, characterId);
    }

    @PostMapping("/{creatorId}/comics")
    public ComicDto assignComic(@PathVariable UUID creatorId, @RequestBody ComicCreateDto comicCreateDto) {
        return creatorService.assignComic(creatorId, comicCreateDto);
    }

    @PatchMapping("/{creatorId}/comics/{comicId}")
    public ComicDto updateComic(@PathVariable UUID creatorId, @PathVariable UUID comicId,
                                @RequestBody ComicUpdateDto comicUpdateDto) {
        return creatorService.updateComic(creatorId, comicId, comicUpdateDto);
    }

    @DeleteMapping("/{creatorId}/comics/{comicId}")
    public void deleteComic(@PathVariable UUID creatorId, @PathVariable UUID comicId) {
        creatorService.deleteComic(creatorId, comicId);
    }

    @PostMapping("/{creatorId}/images")
    public ImageDto uploadImage(@PathVariable UUID creatorId, @RequestParam MultipartFile image) {
        return creatorService.uploadImage(creatorId, image);
    }

    @DeleteMapping("{creatorId}/images/{imageId}")
    public void deleteImage(@PathVariable UUID creatorId, @PathVariable UUID imageId) {
        creatorService.deleteImage(creatorId, imageId);
    }
}