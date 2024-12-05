package com.example.dz7Api.dto;

public class MusicCategoryDTO {
    private Long idMusic;
    private String musicName;
    private String musicLink;
    private String musicDuration;
    private String musicGenre;
    private String musicCountry;
    private String category;


    public MusicCategoryDTO(){}

    
    public MusicCategoryDTO(Long idMusic, String musicName, String musicLink, String musicDuration, String musicGenre,
            String musicCountry, String category) {
        this.idMusic = idMusic;
        this.musicName = musicName;
        this.musicLink = musicLink;
        this.musicDuration = musicDuration;
        this.musicGenre = musicGenre;
        this.musicCountry = musicCountry;
        this.category = category;
    }


    public Long getIdMusic() {
        return idMusic;
    }


    public void setIdMusic(Long idMusic) {
        this.idMusic = idMusic;
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


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }
}
