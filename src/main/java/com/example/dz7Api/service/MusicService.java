package com.example.dz7Api.service;

import com.example.dz7Api.models.Artist;
import com.example.dz7Api.models.Music;
import com.example.dz7Api.repository.MusicRepository;
import com.example.dz7Api.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;

    
    public MusicService(MusicRepository musicRepository, ArtistRepository artistRepository) {
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
    }


    public List<Music> findAll() {
        return musicRepository.findAll();
    }


    @Transactional
    public Music saveMusic(Music music) {
        Set<Artist> savedArtists = new HashSet<>();
        
        for (Artist artist : music.getArtists()) {
            if (artist.getIdArtist() == null) {
                artist = artistRepository.save(artist);
            } else {
                artist = artistRepository.findById(artist.getIdArtist())
                        .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
            }
            savedArtists.add(artist);
        }
        music.setArtists(savedArtists);
        return musicRepository.save(music);
    }


    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Music not found"));
    }


    @Transactional
    public void deleteMusic(Long idMusic) {
        Music music = musicRepository.findById(idMusic)
                .orElseThrow(() -> new IllegalArgumentException("Music not found"));
        music.getArtists().clear();
        musicRepository.delete(music);
    }
}
