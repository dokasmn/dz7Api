package com.example.dz7Api.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@Entity
public class Artist {
    
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArtist;

    @Getter
    @Setter
    private String artistName;

    @Getter
    @Setter
    private String artistProfileLink;

    public Artist(String artistName, String artistProfileLink) {
        if (artistName == null || artistName.isEmpty()) {
            throw new IllegalArgumentException("O artista deve possuir um nome!");
        }

        if (artistProfileLink == null || artistProfileLink.isEmpty()) {
            throw new IllegalArgumentException("O artista deve possuir um link de perfil!");
        }

        this.artistName = artistName;
        this.artistProfileLink = artistProfileLink;
    }
}
