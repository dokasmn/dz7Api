package com.example.dz7Api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dz7Api.Models.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {}
