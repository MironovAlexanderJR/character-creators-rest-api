package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.series.SeriesCreateDto;
import ru.mironov.marvelapi.domain.dto.series.SeriesDto;
import ru.mironov.marvelapi.domain.dto.series.SeriesInfoDto;
import ru.mironov.marvelapi.domain.dto.series.SeriesUpdateDto;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "series")
public class SeriesController {

    @GetMapping("/{seriesId}")
    public SeriesDto getSeries(@PathVariable Long seriesId) {
        return null;
    }

    @GetMapping("/info/{seriesId}")
    public SeriesInfoDto getSeriesInfo(@PathVariable Long seriesId) {
        return null;
    }

    @PostMapping
    public SeriesDto createSeries(@RequestBody SeriesCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{seriesId}")
    public SeriesDto updateSeries(@PathVariable Long seriesId, @RequestBody SeriesUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("{seriesId}")
    public void deleteSeries(@PathVariable Long seriesId) {

    }
}
