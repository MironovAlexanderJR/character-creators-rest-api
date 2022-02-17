package ru.mironov.marvelapi.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.entity.Creator;

import java.util.List;
import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CreatorService {
    List<Creator> getAllCreator();

    Creator getCreator(UUID creatorId);

    Creator createCreator(Creator creatorJson);

    Creator updateCreator(UUID creatorId, Creator creatorJson);

    void deleteCreator(UUID creatorId);

    Comic assignComic(UUID creatorId, Comic comicCreateDto);

    Comic updateComic(UUID creatorsId, UUID comicId, Comic comicUpdateDto);

    void deleteComic(UUID creatorsId, UUID comicId);

    Character assignCharacter(UUID creatorId, Character characterCreateDto);

    Character updateCharacter(UUID creatorsId, UUID characterId, Character characterUpdateDto);

    void deleteCharacter(UUID creatorsId, UUID characterId);
}
