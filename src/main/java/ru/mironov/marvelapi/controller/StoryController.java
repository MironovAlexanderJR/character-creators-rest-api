package ru.mironov.marvelapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mironov.marvelapi.domain.dto.story.StoryCreateDto;
import ru.mironov.marvelapi.domain.dto.story.StoryDto;
import ru.mironov.marvelapi.domain.dto.story.StoryInfoDto;
import ru.mironov.marvelapi.domain.dto.story.StoryUpdateDto;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "stories")
public class StoryController {

    @GetMapping("/{storyId}")
    public StoryDto getStory(@PathVariable Long storyId) {
        return null;
    }

    @GetMapping("/info/{storyId}")
    public StoryInfoDto getStoryInfo(@PathVariable Long storyId) {
        return null;
    }

    @PostMapping
    public StoryDto createStory(@RequestBody StoryCreateDto createDto) {
        return null;
    }

    @PatchMapping("/{storyId}")
    public StoryDto updateStory(@PathVariable Long storyId, @RequestBody StoryUpdateDto updateDto) {
        return null;
    }

    @DeleteMapping("{storyId}")
    public void deleteStory(@PathVariable Long storyId) {

    }
}
