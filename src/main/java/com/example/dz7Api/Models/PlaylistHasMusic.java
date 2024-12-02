package com.example.dz7Api.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "playlist_has_music")
@Getter
@Setter
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