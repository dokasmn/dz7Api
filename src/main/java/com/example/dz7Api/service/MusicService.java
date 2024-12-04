package com.example.dz7Api.service;

import com.example.dz7Api.models.Artist;
import com.example.dz7Api.models.Music;
import com.example.dz7Api.repository.MusicRepository;
import com.example.dz7Api.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;

    public MusicService(MusicRepository musicRepository, ArtistRepository artistRepository) {
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
    }

    @Transactional
    public Music saveMusic(Music music) {
        for (Artist artist : music.getArtists()) {
            if (artistRepository.existsByMusicsContainingAndArtistName(music, artist.getArtistName())) {
                throw new IllegalArgumentException("Music already exists for this artist");
            }
        }

        return musicRepository.save(music);
    }


    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Music not found"));
    }

    public void deleteMusic(Long id) {
        musicRepository.deleteById(id);
    }
}
