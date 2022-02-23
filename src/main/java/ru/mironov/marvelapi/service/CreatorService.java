package ru.mironov.marvelapi.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorCreateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorInfoDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Creator;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CreatorService {
    Page<CreatorDto> getAllCreator(Pageable pageable);

    CreatorDto getAndInitializeCreator(UUID creatorId);

    CreatorInfoDto getCreatorInfo(UUID creatorId);

    Creator localMethodGetCreatorById(UUID creatorId);

    CreatorDto createCreator(CreatorCreateDto creatorJson);

    CreatorDto updateCreator(UUID creatorId, CreatorUpdateDto creatorJson);

    void deleteCreator(UUID creatorId);

    ComicDto assignComic(UUID creatorId, ComicCreateDto comicCreateDto);

    ComicDto updateComic(UUID creatorsId, UUID comicId, ComicUpdateDto comicUpdateDto);

    void deleteComic(UUID creatorsId, UUID comicId);

    CharacterDto assignCharacter(UUID creatorId, CharacterCreateDto characterCreateDto);

    CharacterDto updateCharacter(UUID creatorsId, UUID characterId, CharacterUpdateDto characterUpdateDto);

    void deleteCharacter(UUID creatorsId, UUID characterId);

    ImageDto uploadImage(UUID creatorsId, MultipartFile image);

    void deleteImage(UUID creatorId, UUID imageId);
}
