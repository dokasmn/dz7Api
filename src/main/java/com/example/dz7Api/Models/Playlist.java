package com.example.dz7Api.Models;

import java.util.HashSet;
import java.util.Set;

import com.example.dz7Api.Models.base.BaseModel;
import com.example.dz7Api.Models.base.BaseUser;

// jakarta
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "playlist")
public class Playlist extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name_playlist")
    private String playlistName;
    
    @ManyToMany
    @JoinTable(
        name = "playlist_has_music",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "music_id")
        )
    private Set<Music> playlistMusics = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private BaseUser playlistUser;
    
    
    public Playlist(){}
    

    public Playlist(Long id, String playlistName, Set<Music> playlistMusics, BaseUser playlistUser) {
        this.id = id;
        this.playlistName = playlistName;
        this.playlistMusics = playlistMusics;
        this.playlistUser = playlistUser;
    }
    

    public Long getId() {
        return id;
    }
    

    public void setId(Long idPlaylist) {
        this.id = idPlaylist;
    }
    
    
    public String getPlaylistName() {
        return playlistName;
    }
    
    
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    

    public Set<Music> getPlaylistMusics() {
        return playlistMusics;
    }

    
    public void setPlaylistMusics(Set<Music> playlistMusics) {
        this.playlistMusics = playlistMusics;
    }
    
    
    public BaseUser getPlaylistUser() {
        return playlistUser;
    }
    
    
    public void setPlaylistUser(BaseUser playlistUser) {
        this.playlistUser = playlistUser;
    }
}