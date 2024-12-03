package com.example.dz7Api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "artista")
public class Artist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private int idArtist;

    @Column(name = "nome_artista")
    @NotNull(message = "O artista deve ter um nome!")
    @Size(min = 1, message = "Nome inválido! Tente novamente.")
    private String artistName;

    @Column(name = "link_artista")
    @NotNull(message = "O artista deve ter um link para o perfil!")
    @Size(min = 1, message = "Link inválido! tente novamente.")
    private String artistProfileLink;
}
