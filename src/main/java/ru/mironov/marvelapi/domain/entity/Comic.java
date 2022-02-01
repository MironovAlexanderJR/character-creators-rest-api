package ru.mironov.marvelapi.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
