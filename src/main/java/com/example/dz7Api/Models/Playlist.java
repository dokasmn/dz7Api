package com.example.dz7Api.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlaylist;

    @NotNull(message = "A playlist deve ter um nome!")
    @Size(min = 1, message = "Nome inválido! Tente novamente")
    private String playlistName;
}
                