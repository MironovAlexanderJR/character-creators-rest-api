package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.creator.CreatorCreateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorInfoDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorUpdateDto;
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
}
