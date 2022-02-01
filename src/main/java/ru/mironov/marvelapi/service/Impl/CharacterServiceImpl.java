package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.repository.CharacterRepository;
import ru.mironov.marvelapi.service.CharacterService;

import java.util.Optional;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public Character getCharacter(Long characterId) {
        return characterRepository.findById(characterId).orElseThrow();
    }

    @Override
    public Character createCharacter(Character characterJson) {
        return characterRepository.save(characterJson);
    }

    @Override
    public Character updateCharacter(Long characterId, Character characterJson) {
        return Optional.of(characterId)
                .map(this::getCharacter)
                .map(current -> characterMapper.merge(current, characterJson))
                .map(characterRepository::save)
                .orElseThrow();
    }

    @Override
    public void deleteCharacter(Long characterId) {
        final Character character = characterRepository.findById(characterId).orElseThrow();
        characterRepository.delete(character);
    }
}
