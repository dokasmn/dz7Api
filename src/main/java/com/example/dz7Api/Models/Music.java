package com.example.dz7Api.models;

import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_music")
    private Long idMusic;

    @Column(name = "name_music")
    private String musicName;
     
    @Column(name = "link_music")
    private String musicLink;
    
    @Column(name = "duration_music")
    private Duration musicDuration;
    
    @Column(name = "genre_music")
    private String musicGenre;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    
    
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
    
    public Duration getMusicDuration() {
        return musicDuration;
    }
    
    public void setMusicDuration(Duration musicDuration) {
        this.musicDuration = musicDuration;
    }
    
    public String getMusicGenre() {
        return musicGenre;
    }
    
    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
}
