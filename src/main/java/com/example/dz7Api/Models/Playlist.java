package com.example.dz7Api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_playlist")
    private int idPlaylist;

    @NotNull(message = "A playlist deve ter um nome!")
    @Size(min = 1, message = "Nome inválido! Tente novamente")
    @Column(name = "nome_playlist")
    private String playlistName;
}
                