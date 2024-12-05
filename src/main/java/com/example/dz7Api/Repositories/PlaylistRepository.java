package com.example.dz7Api.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dz7Api.Models.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllByUserId(Long userId);

    Optional<Playlist> findByIdAndUserId(Long playlistId, Long userId);
}

