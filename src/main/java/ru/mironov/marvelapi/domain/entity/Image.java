package ru.mironov.marvelapi.domain.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    public Image(String fileName, String fileType, long size, byte[] data, String authorId) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.data = data;
        this.authorId = authorId;
    }
}
