package ru.mironov.marvelapi.service;

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
}
