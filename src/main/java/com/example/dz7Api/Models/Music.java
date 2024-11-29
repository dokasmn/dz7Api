package com.example.dz7Api.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;


@Getter
@Setter
@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMusic;
    private String musicName;
    private String musicLink;

    // para duração é possível utilizar o Duration, que é mais preciso e flexível, conferir possibilidade
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

    // Pra gerar um construtor sem parametros pode-se usar o Lombok, conferir necessidade
    // @NoArgsConstructor
}

