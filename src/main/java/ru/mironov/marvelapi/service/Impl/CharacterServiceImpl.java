package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.exception.character.CharacterNotFoundException;
import ru.mironov.marvelapi.domain.mapper.CharacterMapper;
import ru.mironov.marvelapi.repository.CharacterRepository;
import ru.mironov.marvelapi.service.CharacterService;

import java.util.List;
import java.util.Optional;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character getCharacter(Long characterId) {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new CharacterNotFoundException(characterId));
    }

    @Override
    @Transactional
    public Character createCharacter(Character characterJson) {
        return characterRepository.save(characterJson);
    }

    @Override
    @Transactional
    public Character updateCharacter(Long characterId, Character characterJson) {
        return Optional.of(characterId)
                .map(this::getCharacter)
                .map(current -> characterMapper.merge(current, characterJson))
                .map(characterRepository::save)
                .orElseThrow(() -> new CharacterNotFoundException(characterId));
    }

    @Override
    @Transactional
    public void deleteCharacter(Long characterId) {
        characterRepository.deleteById(characterId);
    }
}
