package ru.mironov.marvelapi.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.repository.CharacterRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterControllerTest extends AbstractTest {

    @Autowired
    private CharacterRepository characterRepository;

    @AfterEach
    void deleteEntities() {
        characterRepository.deleteAll();
    }

    @Test
    public void testGetCharacter() {
        Character character = new Character();
        character.setName("Character");
        character.setDescription("description");
        characterRepository.save(character);

        CreatorDto excepted = getClassPathResourceAsObject("/excepted/character/get_character.json", new TypeReference<>() {
        });

        webTestClient
                .get()
                .uri("/characters/{characterId}", character.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CharacterDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testGetCharacterInfo() {
        Character character = new Character();
        character.setName("Character");
        character.setDescription("description");
        characterRepository.save(character);

        CreatorDto excepted = getClassPathResourceAsObject("/excepted/character/get_character_info.json", new TypeReference<>() {
        });

        webTestClient
                .get()
                .uri("/characters/info/{characterId}", character.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CharacterInfoDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testCreateCharacter() {
        CharacterCreateDto request = getClassPathResourceAsObject("/request/character/create_character.json", new TypeReference<>() {
        });
        CharacterDto excepted = getClassPathResourceAsObject("/excepted/character/created_character.json", new TypeReference<>() {
        });
        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/characters").build())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CharacterDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted)
                );
    }

    @Test
    public void testUpdateCharacter() {
        Character character = new Character();
        character.setName("name");

        characterRepository.save(character);

        CharacterUpdateDto request = getClassPathResourceAsObject("/request/character/update_character.json", new TypeReference<>() {
        });
        CharacterDto excepted = getClassPathResourceAsObject("/excepted/character/update_character.json", new TypeReference<>() {
        });
        webTestClient
                .patch()
                .uri("/characters/{characterId}", character.getId())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CharacterDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testDeleteCreator() {
        Character character = new Character();
        character.setName("name");

        characterRepository.save(character);

        webTestClient
                .delete()
                .uri("/characters/{characterId}", character.getId())
                .exchange()
                .expectStatus().isOk();
    }
}