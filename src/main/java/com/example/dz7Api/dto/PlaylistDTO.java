package com.example.dz7Api.dto;

public class PlaylistDTO {
    private String playlistName;
    private Long playlistUser;
    private String userPassword;

    public PlaylistDTO() {}

    public PlaylistDTO(String playlistName, Long playlistUser, String userPassword) {
        this.playlistName = playlistName;
        this.playlistUser = playlistUser;
        this.userPassword = userPassword;
    }

    
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Long getPlaylistUser() {
        return playlistUser;
    }

    public void setPlaylistUser(Long playlistUser) {
        this.playlistUser = playlistUser;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}