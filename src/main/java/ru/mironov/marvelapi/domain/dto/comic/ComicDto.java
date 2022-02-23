package ru.mironov.marvelapi.domain.dto.comic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class ComicDto {
    UUID id;
    String name;
    String description;
    String imageDownloadUrl;
}
