package com.example.dz7Api.Models;

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
@Table(name = "artist_has_music")
@Getter
@Setter
@NoArgsConstructor

public class ArtistHasMusic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idMusic", nullable = false)
    private Music music;

    @ManyToOne
    @JoinColumn(name = "idArtist", nullable = false)
    private Artist artist;
}
