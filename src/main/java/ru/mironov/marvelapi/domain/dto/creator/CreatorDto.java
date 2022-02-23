package ru.mironov.marvelapi.domain.dto.creator;

import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class CreatorDto {
    UUID id;
    String name;
    String description;
    String imageDownloadUrl;
    Set<CharacterDto> characters;
    Set<ComicDto> comics;
}
