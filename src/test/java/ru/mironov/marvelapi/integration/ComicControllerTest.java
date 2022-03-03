package ru.mironov.marvelapi.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.repository.ComicRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class ComicControllerTest extends AbstractTest {

    @Autowired
    private ComicRepository comicRepository;

    @AfterEach
    void deleteEntities() {
        comicRepository.deleteAll();
    }

    @Test
    public void testGetComic() {
        Comic comic = new Comic();
        comic.setName("Comic");
        comic.setDescription("description");
        comicRepository.save(comic);

        CreatorDto excepted = getClassPathResourceAsObject("/excepted/comic/get_comic.json", new TypeReference<>() {
        });

        webTestClient
                .get()
                .uri("/comics/{comicId}", comic.getId())
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
    public void testGetComicInfo() {
        Comic comic = new Comic();
        comic.setName("Comic");
        comic.setDescription("description");
        comicRepository.save(comic);

        CreatorDto excepted = getClassPathResourceAsObject("/excepted/comic/get_comic_info.json", new TypeReference<>() {
        });

        webTestClient
                .get()
                .uri("/comics/info/{comicId}", comic.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComicInfoDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted));
    }

    @Test
    public void testCreateComic() {
        ComicCreateDto request = getClassPathResourceAsObject("/request/comic/create_comic.json", new TypeReference<>() {
        });
        ComicDto excepted = getClassPathResourceAsObject("/excepted/comic/created_comic.json", new TypeReference<>() {
        });
        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/comics").build())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComicDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(excepted)
                );
    }

    @Test
    public void testUpdateComic() {
        Comic comic = new Comic();
        comic.setName("name");

        comicRepository.save(comic);

        ComicUpdateDto request = getClassPathResourceAsObject("/request/comic/update_comic.json", new TypeReference<>() {
        });
        ComicDto excepted = getClassPathResourceAsObject("/excepted/comic/update_comic.json", new TypeReference<>() {
        });
        webTestClient
                .patch()
                .uri("/comics/{comicId}", comic.getId())
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
        Comic comic = new Comic();
        comic.setName("name");

        comicRepository.save(comic);

        webTestClient
                .delete()
                .uri("/comics/{comicId}", comic.getId())
                .exchange()
                .expectStatus().isOk();
    }
}
