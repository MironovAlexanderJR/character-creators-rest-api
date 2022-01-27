package ru.mironov.marvelapi.domain.dto.character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class CharacterCreateDto {
    String name;
    String description;
    ComicCreateDto comicCreateDto;
}
