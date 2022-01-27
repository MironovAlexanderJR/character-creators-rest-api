package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.creator.CreatorCreateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorInfoDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorUpdateDto;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "creators")
public class CreatorController {

    @GetMapping("/{creatorsId}")
    public CreatorDto getCreator(@PathVariable Long creatorsId) {
        return null;
    }

    @GetMapping("/info/{creatorsId}")
    public CreatorInfoDto getCreatorInfo(@PathVariable Long creatorsId) {
        return null;
    }

    @PostMapping
    public CreatorDto createCreator(@RequestBody CreatorCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{creatorsId}")
    public CreatorDto updateCreator(@PathVariable Long creatorsId, @RequestBody CreatorUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("{creatorsId}")
    public void deleteCreator(@PathVariable Long creatorsId) {

    }
}
