package com.example.dz7Api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dz7Api.models.Artist;
import com.example.dz7Api.models.Category;
import com.example.dz7Api.models.Playlist;
import com.example.dz7Api.repository.PlaylistRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
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
