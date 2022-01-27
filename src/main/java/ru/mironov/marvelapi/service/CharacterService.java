package ru.mironov.marvelapi.service;

import ru.mironov.marvelapi.domain.entity.Character;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CharacterService {
    Character getCharacter(Long characterId);

    Character createCharacter(Character characterJson);

    Character updateCharacter(Long characterId, Character characterJson);

    void delete(Long characterId);
}
