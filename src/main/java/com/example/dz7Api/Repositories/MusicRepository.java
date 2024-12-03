package com.example.dz7api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dz7api.models.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {}
