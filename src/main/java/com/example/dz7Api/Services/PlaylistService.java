package com.example.dz7Api.Services;

import org.springframework.stereotype.Service;

import com.example.dz7Api.Models.Playlist;
import com.example.dz7Api.Repositories.PlaylistRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist savePlaylist(Playlist playlist){
        return playlistRepository.save(playlist);
    }

    public Playlist getPlaylistById(Long id){
        return playlistRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Playlist not found!"));
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
}
