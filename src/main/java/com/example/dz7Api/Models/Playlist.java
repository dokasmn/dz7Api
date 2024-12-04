package com.example.dz7Api.Models;

import java.util.HashSet;
import java.util.Set;

import com.example.dz7Api.Models.base.BaseModel;

// jakarta
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "playlist")
public class Playlist extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_playlist")
    private Long idPlaylist;
    
    @Column(name = "name_playlist")
    private String playlistName;
    
    @ManyToMany
    @JoinTable(
        name = "playlist_has_music",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "music_id")
        )
    private Set<Music> musics = new HashSet<>();
        
    
    public Playlist(){}
    

    public Playlist(Long idPlaylist, String playlistName, Set<Music> musics) {
        this.idPlaylist = idPlaylist;
        this.playlistName = playlistName;
        this.musics = musics;
    }
    

    public Long getIdPlaylist() {
        return idPlaylist;
    }
    

    public void setIdPlaylist(Long idPlaylist) {
        this.idPlaylist = idPlaylist;
    }
    

    public String getPlaylistName() {
        return playlistName;
    }
    
    
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }


    public Set<Music> getMusics() {
        return musics;
    }


    public void setMusics(Set<Music> musics) {
        this.musics = musics;
    }
}