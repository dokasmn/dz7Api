package com.example.dz7Api.Models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    
    @Column(name = "music_duration")
    private String musicDuration;
    
    @Column(name = "genre_music")
    private String musicGenre;

    @Column(name = "music_country")
    private String musicCountry;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
    
    @ManyToMany
    @JoinTable(
        name = "artist_has_music",
        joinColumns = @JoinColumn(name = "music_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artists = new HashSet<>();
    

    public Music(){}
    

    public Music(Long idMusic, String musicName, String musicLink, String musicDuration, String musicGenre,
            String musicCountry, Category category, Set<Artist> artists) {
        this.idMusic = idMusic;
        this.musicName = musicName;
        this.musicLink = musicLink;
        this.musicDuration = musicDuration;
        this.musicGenre = musicGenre;
        this.musicCountry = musicCountry;
        this.category = category;
        this.artists = artists;
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


    public Category getCategory() {
        return category;
    }
    

    public void setCategory(Category category) {
        this.category = category;
    }
    

    public Set<Artist> getArtists() {
        return artists;
    }
    

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }
}
