package com.example.dz7Api.models;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "artist")
public class Artist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artist")
    private int idArtist;

    @Column(name = "name_artist")
    private String artistName;

    @Column(name = "link_artist")
    private String artistProfileLink;

    @ManyToMany
    @JoinTable(
        name = "artist_has_music",
        joinColumns = @JoinColumn(name = "artist_id"),
        inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private Set<Music> artistMusics = new HashSet<>();
    

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistProfileLink() {
        return artistProfileLink;
    }

    public void setArtistProfileLink(String artistProfileLink) {
        this.artistProfileLink = artistProfileLink;
    }

    public Set<Music> getArtistMusics() {
        return artistMusics;
    }

    public void setArtistMusics(Set<Music> artistMusics) {
        this.artistMusics = artistMusics;
    }
}