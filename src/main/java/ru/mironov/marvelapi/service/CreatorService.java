package ru.mironov.marvelapi.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.entity.Creator;

import java.util.List;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CreatorService {
    List<Creator> getAllCreator();

    Creator getCreator(Long creatorId);

    Creator createCreator(Creator creatorJson);

    Creator updateCreator(Long creatorId, Creator creatorJson);

    void deleteCreator(Long creatorId);

    Comic assignComic(Long creatorId, Comic comicCreateDto);

    Comic updateComic(Long creatorsId, Long comicId, Comic comicUpdateDto);

    void deleteComic(Long creatorsId, Long comicId);

    Character assignCharacter(Long creatorId, Character characterCreateDto);

    Character updateCharacter(Long creatorsId, Long characterId, Character characterUpdateDto);

    void deleteCharacter(Long creatorsId, Long characterId);
}
