package ru.mironov.marvelapi.domain.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Getter
@Setter
@Entity
@Table(name = "characters")
public class Character extends BaseEntity {
    private String name;
    private String description;
    private String imageDownloadUrl;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "characters_comics",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "characters_creator",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id"))
    private Set<Creator> creators;

    public void addComic(Comic comic) {
        this.comics.add(comic);
        comic.getCharacters().add(this);
    }

    public void deleteComic(Comic comic) {
        this.comics.remove(comic);
    }
}
