package ru.mironov.marvelapi.domain.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
@AllArgsConstructor
@Data
public class ImageDto {
    private UUID id;
    private String fileName;
    private String fileType;
    private long size;
}
