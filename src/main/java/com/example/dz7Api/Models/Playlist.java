package com.example.dz7Api.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@Entity
public class Playlist {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlaylist;

    @Getter
    @Setter 
    private String playlistName;

    public Playlist(String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            throw new IllegalArgumentException("A playlist deve possuir um nome");
        }

        this.playlistName = playlistName;
    }
}
