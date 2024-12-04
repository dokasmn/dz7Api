package com.example.dz7Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dz7Api.models.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

}
