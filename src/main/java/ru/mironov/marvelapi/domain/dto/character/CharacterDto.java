package ru.mironov.marvelapi.domain.dto.character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class CharacterDto {
    Long id;
    String name;
    String description;
}
