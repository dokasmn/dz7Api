package com.example.dz7Api.dto;

public class MusicCreateDTO {
    private String musicName;
    private String musicLink;
    private String musicDuration;
    private String musicGenre;
    private String musicCountry;
    private Long categoryId;


    public MusicCreateDTO(){}

    
    public MusicCreateDTO(String musicName, String musicLink, String musicDuration, String musicGenre,
            String musicCountry, Long categoryId) {
        this.musicName = musicName;
        this.musicLink = musicLink;
        this.musicDuration = musicDuration;
        this.musicGenre = musicGenre;
        this.musicCountry = musicCountry;
        this.categoryId = categoryId;
    }


    public String getMusicName() {
        return musicName;
    }


    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }


    public String getMusicLink() {
        return musicLink;
    }


    public void setMusicLink(String musicLink) {
        this.musicLink = musicLink;
    }


    public String getMusicDuration() {
        return musicDuration;
    }


    public void setMusicDuration(String musicDuration) {
        this.musicDuration = musicDuration;
    }


    public String getMusicGenre() {
        return musicGenre;
    }


    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }


    public String getMusicCountry() {
        return musicCountry;
    }


    public void setMusicCountry(String musicCountry) {
        this.musicCountry = musicCountry;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
}
