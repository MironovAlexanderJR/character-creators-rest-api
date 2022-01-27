package ru.mironov.marvelapi.domain.dto.character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.event.EventDto;
import ru.mironov.marvelapi.domain.dto.series.SeriesDto;
import ru.mironov.marvelapi.domain.dto.story.StoryDto;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class CharacterInfoDto {
    Long id;
    String name;
    String description;
    Long comicId;
    Long creatorId;
    Long eventId;
    Long seriesId;
    Long storyId;
}
