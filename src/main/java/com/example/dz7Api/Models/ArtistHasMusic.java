package com.example.dz7Api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "artista_musica")
@Getter
@Setter
@NoArgsConstructor

public class ArtistHasMusic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idMusic", nullable = false)
    @Column(name = "musica_id_musica")
    private Music music;

    @ManyToOne
    @JoinColumn(name = "idArtist", nullable = false)
    @Column(name = "artista_id_artista")
    private Artist artist;
}
