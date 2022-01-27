package ru.mironov.marvelapi.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Getter
@Setter
@Entity
@Table(name = "comics")
public class Comic extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "characters_comics",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Character> character;
}
