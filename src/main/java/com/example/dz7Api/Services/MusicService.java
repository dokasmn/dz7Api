package com.example.dz7Api.Services;

import org.springframework.stereotype.Service;

import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Repositories.MusicRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MusicService {
    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Music saveMusic(Music music) {
        // if (musicRepository.existsByTitleAndArtistName(music.getMusicName(), music.getArtistName())) {
        //     throw new IllegalArgumentException("Music already exists");
        // }
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
