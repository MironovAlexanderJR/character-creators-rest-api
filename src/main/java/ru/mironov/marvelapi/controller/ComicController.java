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
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.service.ComicService;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "comics")
public class ComicController {
    private final ComicService comicService;

    @GetMapping("/")
    public Page<ComicDto> getAllComic(Pageable pageable) {
        return comicService.getAllComics(pageable);
    }

    @GetMapping("/{comicId}")
    public ComicDto getComic(@PathVariable UUID comicId) {
        return comicService.getAndInitializeComic(comicId);
    }

    @GetMapping("/info/{comicId}")
    public ComicInfoDto getCharacterInfo(@PathVariable UUID comicId) {
        return comicService.getComicInfo(comicId);
    }

    @PostMapping
    public ComicDto createComic(@Valid @RequestBody ComicCreateDto comicCreateDto) {
        return comicService.createComic(comicCreateDto);
    }

    @PatchMapping("/{comicId}")
    public ComicDto updateComic(@PathVariable UUID comicId, @Valid @RequestBody ComicUpdateDto comicUpdateDto) {
        return comicService.updateComic(comicId, comicUpdateDto);
    }

    @DeleteMapping("{comicId}")
    public void deleteComic(@PathVariable UUID comicId) {
        comicService.deleteComic(comicId);
    }

    @PostMapping("/{comicId}/images")
    public ImageDto uploadImage(@PathVariable UUID comicId, @RequestParam MultipartFile image) {
        return comicService.uploadImage(comicId, image);
    }

    @DeleteMapping("{comicId}/images/{imageId}")
    public void deleteImage(@PathVariable UUID comicId, @PathVariable UUID imageId) {
        comicService.deleteImage(comicId, imageId);
    }
}
