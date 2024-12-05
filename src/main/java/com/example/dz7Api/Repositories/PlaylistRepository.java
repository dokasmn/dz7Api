package com.example.dz7Api.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dz7Api.Models.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllByPlaylistUserId(Long userId);
    Optional<Playlist> findByIdAndPlaylistUserId(Long playlistId, Long userId);
}