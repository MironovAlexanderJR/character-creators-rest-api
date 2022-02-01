package ru.mironov.marvelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mironov.marvelapi.domain.entity.Comic;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface ComicRepository extends JpaRepository<Comic, Long> {
}
