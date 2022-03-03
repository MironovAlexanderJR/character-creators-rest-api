package ru.mironov.marvelapi.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorCreateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorInfoDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorUpdateDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.entity.Creator;
import ru.mironov.marvelapi.repository.CharacterRepository;
import ru.mironov.marvelapi.repository.ComicRepository;
import ru.mironov.marvelapi.repository.CreatorRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author mironovAlexanderJR
 * @since 01.02.2022
 */
public class CreatorControllerTest extends AbstractTest {
    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ComicRepository comicRepository;

    @AfterEach
    void deleteEntities() {
        characterRepository.deleteAll();
        comicRepository.deleteAll();
        creatorRepository.deleteAll();
    }
    
    @Test
    public void testGetCreator() {
        Creator creator = new Creator();
        creator.setName("name");
        creatorRepository.save(creator);

        CreatorDto excepted = getClassPathResourceAsObject("/excepted/creator/get_creator.json", new TypeReference<>() {
        });

        webTestClient
                .get()
                .uri("/creators/{creatorId}", creator.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreatorDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testGetCreatorInfo() {
        Creator creator = new Creator();
        creator.setName("name");
        creator.setDescription("description");
        creatorRepository.save(creator);

        CreatorDto excepted = getClassPathResourceAsObject("/excepted/creator/get_creator_info.json", new TypeReference<>() {
        });

        webTestClient
                .get()
                .uri("/creators/info/{creatorId}", creator.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreatorInfoDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testCreateCreator() {
        CreatorCreateDto request = getClassPathResourceAsObject("/request/creator/create_creator.json", new TypeReference<>() {
        });
        CreatorDto excepted = getClassPathResourceAsObject("/excepted/creator/created_creator.json", new TypeReference<>() {
        });
        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/creators").build())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreatorDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted)
                );
    }

    @Test
    public void testUpdateCreator() {
        Creator creator = new Creator();
        creator.setName("name");

        creatorRepository.save(creator);

        CreatorUpdateDto request = getClassPathResourceAsObject("/request/creator/update_creator.json", new TypeReference<>() {
        });
        CreatorDto excepted = getClassPathResourceAsObject("/excepted/creator/update_creator.json", new TypeReference<>() {
        });
        webTestClient
                .patch()
                .uri("/creators/{creatorId}", creator.getId())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreatorDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testDeleteCreator() {
        Creator creator = new Creator();
        creator.setName("name");

        creatorRepository.save(creator);

        webTestClient
                .delete()
                .uri("/creators/{creatorId}", creator.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testAssignCharacter() {
        Creator creator = new Creator();
        creator.setName("name");
        creator.setDescription("description");
        creatorRepository.save(creator);

        CharacterCreateDto request = getClassPathResourceAsObject("/request/creator/add_character_creator.json", new TypeReference<>() {
        });
        CharacterDto excepted = getClassPathResourceAsObject("/excepted/creator/add_character_creator.json", new TypeReference<>() {
        });

        webTestClient
                .post()
                .uri("/creators/{creatorId}/characters", creator.getId())
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
    public void testUpdateCharacter() {
        Creator creator = new Creator();
        creator.setName("name");
        creator.setDescription("description");
        creatorRepository.save(creator);

        Character character = new Character();
        character.setName("creator");
        characterRepository.save(character);

        CharacterUpdateDto request = getClassPathResourceAsObject("/request/creator/update_character_creator.json", new TypeReference<>() {
        });
        CharacterDto excepted = getClassPathResourceAsObject("/excepted/creator/update_character_creator.json", new TypeReference<>() {
        });

        webTestClient
                .patch()
                .uri("/creators/{creatorId}/characters/{characterId}", creator.getId(), character.getId())
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
    public void testDeleteCharacter() {
        Creator creator = new Creator();
        creator.setName("name");
        creator.setDescription("description");
        creatorRepository.save(creator);

        Character character = new Character();
        character.setName("creator");
        characterRepository.save(character);

        webTestClient
                .delete()
                .uri("/creators/{creatorId}/characters/{characterId}", creator.getId(), character.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testAssignComics() {
        Creator creator = new Creator();
        creator.setName("name");
        creator.setDescription("description");
        creatorRepository.save(creator);

        ComicCreateDto request = getClassPathResourceAsObject("/request/creator/add_comic_creator.json", new TypeReference<>() {
        });
        ComicDto excepted = getClassPathResourceAsObject("/excepted/creator/add_comic_creator.json", new TypeReference<>() {
        });

        webTestClient
                .post()
                .uri("/creators/{creatorId}/comics", creator.getId())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComicDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testUpdateComic() {
        Creator creator = new Creator();
        creator.setName("name");
        creator.setDescription("description");
        creatorRepository.save(creator);

        Comic comic = new Comic();
        comic.setName("comic");
        comicRepository.save(comic);

        CharacterUpdateDto request = getClassPathResourceAsObject("/request/creator/update_comic_creator.json", new TypeReference<>() {
        });
        CharacterDto excepted = getClassPathResourceAsObject("/excepted/creator/update_comic_creator.json", new TypeReference<>() {
        });

        webTestClient
                .patch()
                .uri("/creators/{creatorId}/comics/{characterId}", creator.getId(), comic.getId())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComicDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testDeleteComic() {
        Creator creator = new Creator();
        creator.setName("name");
        creator.setDescription("description");
        creatorRepository.save(creator);

        Comic comic = new Comic();
        comic.setName("comic");
        comicRepository.save(comic);

        webTestClient
                .delete()
                .uri("/creators/{creatorId}/comics/{characterId}", creator.getId(), comic.getId())
                .exchange()
                .expectStatus().isOk();
    }
}
