package com.example.dz7Api.Models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categoria")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private int idCategory;

    @NotNull(message = "O nome da categoria não pode ser nulo!")
    @Size(min = 1, message = "Nome inválido! Tente novamente.")
    @Column(name = "nome_categoria")
    private String categoryName;

    @NotNull(message = "A temperatura da categoria não pode ser nula!")
    @Size(min = 1, message = "Temperatura inválida! Tente novamente.")
    @Column(name = "temperatura")
    private int categoryTemperature;

    @OneToMany(mappedBy="category")
    private List<Music> musics;

}
