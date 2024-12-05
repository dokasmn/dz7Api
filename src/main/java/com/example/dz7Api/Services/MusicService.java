package com.example.dz7Api.Services;

import com.example.dz7Api.Models.Admin;
import com.example.dz7Api.Models.Artist;
import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.MusicRepository;
import com.example.dz7Api.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final UserRepository userRepository;

    public MusicService(MusicRepository musicRepository, UserRepository userRepository) {
        this.musicRepository = musicRepository;
        this.userRepository = userRepository;
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }


    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Music not found"));
    }



    @Transactional
    public Music saveMusic(Music music, BaseUser user) {
        if (!(user instanceof Artist || user instanceof Admin)) {
            throw new IllegalArgumentException("Only artists and admins can create music.");
        }
        
        List<Artist> savedArtists = new ArrayList<>();
        
        if (user instanceof Admin) {
            for (BaseUser artist : music.getOwners()) {
                BaseUser existingUser = userRepository.findById(artist.getId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + artist.getId()));
                if (existingUser instanceof Artist) {
                    savedArtists.add((Artist) existingUser);
                }
            }
        } else if (user instanceof Artist) {
            savedArtists.add((Artist) user);
        }

        music.setOwners(savedArtists);
        return musicRepository.save(music);
    }


    @Transactional
    public void deleteMusic(Long idMusic, BaseUser user) {
        Music music = musicRepository.findById(idMusic)
                .orElseThrow(() -> new IllegalArgumentException("Música não encontrada"));

        if (user instanceof Admin) {
            musicRepository.delete(music);
        } else if (user instanceof Artist) {
            if (music.getOwners().contains(user)) {
                musicRepository.delete(music);
            } else {
                throw new IllegalArgumentException("Artist is not associated with the music.");
            }
        } else {
            throw new IllegalArgumentException("Only admins or associated artists can delete the music.");
        }
    }


    @Transactional
    public Music updateMusic(Music updatedMusic, Long idMusic, BaseUser user) {
        Music existingMusic = musicRepository.findById(idMusic)
                .orElseThrow(() -> new EntityNotFoundException("Música não encontrada"));

        if (user instanceof Admin) {
            List<Artist> updatedArtists = new ArrayList<>();
            for (BaseUser artist : updatedMusic.getOwners()) {
                BaseUser existingUser = userRepository.findById(artist.getId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + artist.getId()));
                if (existingUser instanceof Artist) {
                    updatedArtists.add((Artist) existingUser);
                }
            }
            existingMusic.setOwners(updatedArtists);
            existingMusic.setMusicName(updatedMusic.getMusicName());
            existingMusic.setMusicGenre(updatedMusic.getMusicGenre());
            return musicRepository.save(existingMusic);
        }

        if (user instanceof Artist) {
            if (existingMusic.getOwners().contains(user)) {
                existingMusic.setMusicName(updatedMusic.getMusicName());
                existingMusic.setMusicGenre(updatedMusic.getMusicGenre());
                return musicRepository.save(existingMusic);
            } else {
                throw new IllegalArgumentException("Artist can only update their own music.");
            }
        }
        
        throw new IllegalArgumentException("Only admins or associated artists can update the music.");
    }
}
