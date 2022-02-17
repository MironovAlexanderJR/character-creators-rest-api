package ru.mironov.marvelapi.service;

import ru.mironov.marvelapi.domain.entity.Character;

import java.util.List;
import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CharacterService {
    List<Character> getAllCharacters();

    Character getCharacter(UUID characterId);

    Character createCharacter(Character characterJson);

    Character updateCharacter(UUID characterId, Character characterJson);

    void deleteCharacter(UUID characterId);
}
