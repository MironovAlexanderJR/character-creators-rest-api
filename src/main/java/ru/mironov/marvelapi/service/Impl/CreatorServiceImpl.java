package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.mironov.marvelapi.domain.entity.Creator;
import ru.mironov.marvelapi.domain.exception.creator.CreatorNotFoundException;
import ru.mironov.marvelapi.domain.mapper.CreatorMapper;
import ru.mironov.marvelapi.repository.CreatorRepository;
import ru.mironov.marvelapi.service.CreatorService;

import java.util.List;
import java.util.Optional;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
public class CreatorServiceImpl implements CreatorService {
    private final CreatorRepository creatorRepository;
    private final CreatorMapper creatorMapper;

    @Override
    public List<Creator> getAllCreator() {
        return creatorRepository.findAll();
    }

    @Override
    public Creator getCreator(Long creatorId) {
        return creatorRepository.findById(creatorId)
                .orElseThrow(() -> new CreatorNotFoundException(creatorId));
    }

    @Override
    public Creator createCreator(Creator creatorJson) {
        return creatorRepository.save(creatorJson);
    }

    @Override
    public Creator updateCreator(Long creatorId, Creator creatorJson) {
        return Optional.of(creatorId)
                .map(this::getCreator)
                .map(current -> creatorMapper.merge(current, creatorJson))
                .map(creatorRepository::save)
                .orElseThrow(() -> new CreatorNotFoundException(creatorId));
    }

    @Override
    public void deleteCreator(Long creatorId) {
        final Creator comic = creatorRepository.findById(creatorId).orElseThrow();
        creatorRepository.delete(comic);
    }
}
