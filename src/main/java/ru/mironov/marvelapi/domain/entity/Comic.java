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
@Table(name = "comics")
public class Comic extends BaseEntity {
    private String name;
    private String description;
    private String imageDownloadUrl;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "characters_comics",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Character> characters;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "comic_creator",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id"))
    private Set<Creator> creators;

    public void addCharacter(Character character) {
        this.getCharacters().add(character);
        character.getComics().add(this);
    }

    public void deleteCharacter(Character character) {
        this.getCharacters().remove(character);
    }
}
