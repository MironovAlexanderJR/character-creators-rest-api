package ru.mironov.marvelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mironov.marvelapi.domain.entity.Creator;

import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface CreatorRepository extends JpaRepository<Creator, UUID> {
}
