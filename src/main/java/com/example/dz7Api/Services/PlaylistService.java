package com.example.dz7Api.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.Playlist;
import com.example.dz7Api.Repositories.MusicRepository;
import com.example.dz7Api.Repositories.PlaylistRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final MusicRepository musicRepository;


    public PlaylistService(PlaylistRepository playlistRepository, MusicRepository musicRepository) {
        this.playlistRepository = playlistRepository;
        this.musicRepository = musicRepository;
    }


    public List<Playlist> findAll(Long userId) {
        return playlistRepository.findAllByUserId(userId);
    }


    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }


    public Playlist getPlaylistById(Long playlistId, Long userId) {
        return playlistRepository.findByIdAndUserId(playlistId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found!"));
    }


    public Playlist getPlaylistById(Long id){
        return playlistRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Playlist not found!"));
    }

    
    public Playlist addMusicToPlaylist(Long playlistId, Long musicId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found!"));
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found!"));

        playlist.getMusics().add(music);
        return playlistRepository.save(playlist);
    }


    public Playlist removeMusicFromPlaylist(Long playlistId, Long musicId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found!"));
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found!"));

        playlist.getMusics().remove(music);
        return playlistRepository.save(playlist);
    }


    public Playlist updatePlaylist(Long playlistId, Playlist updatedPlaylist) {
        Playlist existingPlaylist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found!"));

        existingPlaylist.setPlaylistName(updatedPlaylist.getPlaylistName());
        return playlistRepository.save(existingPlaylist);
    }
}