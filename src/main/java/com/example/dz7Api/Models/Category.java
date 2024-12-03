package com.example.dz7Api.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    @NotNull(message = "O nome da categoria não pode ser nulo!")
    @Size(min = 1, message = "Nome inválido! Tente novamente.")
    private String categoryName;

    @NotNull(message = "A temperatura da categoria não pode ser nula!")
    @Size(min = 1, message = "Temperatura inválida! Tente novamente.")
    private int categoryTemperature;

    @OneToMany(mappedBy="category")
    private List<Music> musics;

}
