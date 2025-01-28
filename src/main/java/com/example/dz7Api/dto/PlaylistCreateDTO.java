package com.example.dz7Api.dto;

import java.util.List;

public class PlaylistCreateDTO {
    private String playlistName;  // Nome da playlist
    private List<Long> playlistMusics;  // Lista de IDs de músicas

    // Getters e Setters
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Long> getPlaylistMusics() {
        return playlistMusics;
    }

    public void setPlaylistMusics(List<Long> playlistMusics) {
        this.playlistMusics = playlistMusics;
    }
}
