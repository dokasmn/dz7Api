package com.example.dz7Api.Services;

import com.example.dz7Api.Models.Admin;
import com.example.dz7Api.Models.Artist;
import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.ArtistRepository;
import com.example.dz7Api.Repositories.MusicRepository;
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
    public Music saveMusic(Music music, BaseUser user) {
        if (!(user instanceof Artist || user instanceof Admin)) {
            throw new IllegalArgumentException("Only artists and admins can create music.");
        }
        if (user instanceof Artist) {
            Artist artist = (Artist) user;
            Set<Artist> savedArtists = new HashSet<>();
            savedArtists.add(artist);
            music.setArtists(savedArtists);
        }
        return musicRepository.save(music);
    }


    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Music not found"));
    }


    @Transactional
    public void deleteMusic(Long idMusic, BaseUser user) {
        Music music = musicRepository.findById(idMusic)
                .orElseThrow(() -> new IllegalArgumentException("Music not found"));

     
        if (user instanceof Admin) {
            musicRepository.delete(music);
        } else if (user instanceof Artist) {
            Artist artist = (Artist) user;
            if (music.getArtists().contains(artist)) {
                musicRepository.delete(music);
            } else {
                throw new IllegalArgumentException("Artist is not the owner of the music.");
            }
        } else {
            throw new IllegalArgumentException("Only admins or the artist who created the music can delete it.");
        }
    }

    
    @Transactional
    public Music updateMusic(Music updatedMusic, Long idMusic, BaseUser user) {
        Music existingMusic = musicRepository.findById(idMusic)
                .orElseThrow(() -> new EntityNotFoundException("Music not found"));
        if (user instanceof Admin) {
            return musicRepository.save(updatedMusic);
        }
        if (user instanceof Artist) {
            Artist artist = (Artist) user;
            if (existingMusic.getArtists().contains(artist)) {
                return musicRepository.save(updatedMusic);
            } else {
                throw new IllegalArgumentException("Artist can only update their own music.");
            }
        }

        throw new IllegalArgumentException("Only admins or the artist who created the music can update it.");
    }
}
