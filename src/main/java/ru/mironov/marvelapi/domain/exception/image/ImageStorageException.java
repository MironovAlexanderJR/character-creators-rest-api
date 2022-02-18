package ru.mironov.marvelapi.domain.exception.image;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
public class ImageStorageException extends RuntimeException {
    public ImageStorageException(String fileName) {
        super("Could not store file " + fileName + ". Please try again!");
    }
}
