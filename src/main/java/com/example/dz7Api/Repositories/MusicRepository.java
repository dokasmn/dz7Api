package com.example.dz7Api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dz7Api.Models.Music;

import com.example.dz7Api.Models.Music;

import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {}
