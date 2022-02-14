package ru.mironov.marvelapi.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Getter
@Setter
@Entity
@Table(name = "creators")
public class Creator extends BaseEntity{
    private String name;
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "characters_creator",
            joinColumns = @JoinColumn(name = "creator_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Character> characters = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "comic_creator",
            joinColumns = @JoinColumn(name = "creator_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics = new HashSet<>();

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public void deleteCharacter(Comic comic) {
        this.comics.remove(comic);
    }

    public void addComic(Comic comic) {
        this.comics.add(comic);
    }

    public void deleteComic(Comic comic) {
        this.comics.remove(comic);
    }
}
