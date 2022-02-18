package ru.mironov.marvelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mironov.marvelapi.domain.entity.Image;

import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
public interface FileRepository extends JpaRepository<Image, UUID> {
    Image findByAuthorId(String authorId);
}
