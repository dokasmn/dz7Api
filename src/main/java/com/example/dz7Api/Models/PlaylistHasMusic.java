package com.example.dz7Api.Models;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "playlist_has_music")
@Getter
@Setter
@NoArgsConstructor
public class PlaylistHasMusic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idPlaylist", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "idMusic", nullable = false)
    private Music music;

}