package com.example.dz7Api.Models;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMusic;
    private String musicName;
    private String musicLink;
    private Time musicDuration;
    private String musicGenre;

    public Music(String musicName, String musicLink, Time musicDuration, String musicGenre) {
        if (musicName == null || musicName.isEmpty()) {
            throw new IllegalArgumentException("A música deve ter um nome!");
        }

        if (musicLink == null || musicLink.isEmpty()) {
            throw new IllegalArgumentException("A música deve ter um link!");
        }

        if (musicDuration == null) {
            throw new IllegalArgumentException("A música deve ter um tempo de duração!");
        }

        if (musicGenre == null || musicGenre.isEmpty()) {
            throw new IllegalArgumentException("A música deve ter um gênero!");
        }

        this.musicName = musicName;
        this.musicLink = musicLink;
        this.musicDuration = musicDuration;
        this.musicGenre = musicGenre;
    }
}
