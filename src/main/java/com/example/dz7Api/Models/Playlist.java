package com.example.dz7Api.Models;

import java.util.HashSet;
import java.util.Set;

import com.example.dz7Api.Models.base.BaseModel;
import com.example.dz7Api.Models.base.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "playlist")
public class Playlist extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
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

    public Playlist() {}

    public Playlist(String playlistName, Set<Music> playlistMusics, BaseUser playlistUser) {
        this.playlistName = playlistName;
        this.playlistMusics = playlistMusics;
        this.playlistUser = playlistUser;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
