package ru.mironov.marvelapi.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "files")
public class Image {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    private String fileName;

    private String fileType;

    private long size;

    @Lob
    private byte[] data;

    @Column(columnDefinition = "varchar(36)")
    private String authorId;

    private String fileDownloadUri;

    public Image(String fileName, String fileType, long size, byte[] data, String authorId) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.data = data;
        this.authorId = authorId;
    }
}
