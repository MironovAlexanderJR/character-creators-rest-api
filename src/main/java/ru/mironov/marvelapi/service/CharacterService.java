package ru.mironov.marvelapi.service;

import ru.mironov.marvelapi.domain.entity.Character;

import java.util.List;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CharacterService {
    List<Character> getAllCharacters();

    Character getCharacter(Long characterId);

    Character createCharacter(Character characterJson);

    Character updateCharacter(Long characterId, Character characterJson);

    void deleteCharacter(Long characterId);
}
