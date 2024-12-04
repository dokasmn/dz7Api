package com.example.dz7Api.models;

import java.util.HashSet;
import java.util.Set;

// jakarta
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtist;

    private String artistName;

    @ManyToMany
    @JoinTable(
      name = "artist_music",
      joinColumns = @JoinColumn(name = "artist_id"),
      inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<Music> musics = new HashSet<>();

    @Column(name = "link_artist")
    private String artistProfileLink;

    
    public Artist() {}

    public Artist(Long idArtist, String artistName, String artistProfileLink) {
        this.idArtist = idArtist;
        this.artistName = artistName;
        this.artistProfileLink = artistProfileLink;
    }

    public Long getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(Long idArtist) {
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
}