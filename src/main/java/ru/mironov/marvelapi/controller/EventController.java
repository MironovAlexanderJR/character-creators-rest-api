package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.event.EventCreateDto;
import ru.mironov.marvelapi.domain.dto.event.EventDto;
import ru.mironov.marvelapi.domain.dto.event.EventInfoDto;
import ru.mironov.marvelapi.domain.dto.event.EventUpdateDto;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "events")
public class EventController {

    @GetMapping("/{eventId}")
    public EventDto getEvent(@PathVariable Long eventId) {
        return null;
    }

    @GetMapping("/info/{eventId}")
    public EventInfoDto getEventInfo(@PathVariable Long eventId) {
        return null;
    }

    @PostMapping
    public EventDto createEvent(@RequestBody EventCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{eventId}")
    public EventDto updateEvent(@PathVariable Long eventId, @RequestBody EventUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {

    }
}
