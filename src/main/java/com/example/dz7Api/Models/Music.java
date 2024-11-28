package com.example.dz7Api.Models;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@Entity
public class Music {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMusic;

    @Getter
    @Setter
    private String musicName;

    @Getter
    @Setter
    private String musicLink;

    @Getter
    @Setter
    private Time musicDurationTime;

    @Getter
    @Setter
    private String musicGenre;

    public Music(String musicName, String musicLink, Time musicDurationTime, String musicGenre) {
        if (musicName == null || musicName.isEmpty()) {
            throw new IllegalArgumentException("A música deve ter um nome!");
        }

        if (musicLink == null || musicLink.isEmpty()) {
            throw new IllegalArgumentException("A música deve ter um link!");
        }

        if (musicDurationTime == null) {
            throw new IllegalArgumentException("A música deve ter um tempo de duração!");
        }

        if (musicGenre == null || musicGenre.isEmpty()) {
            throw new IllegalArgumentException("A música deve ter um gênero!");
        }
        
        this.musicName = musicName;
        this.musicLink = musicLink;
        this.musicDurationTime = musicDurationTime;
        this.musicGenre = musicGenre;
    }
    
}
