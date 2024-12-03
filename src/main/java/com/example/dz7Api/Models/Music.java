package com.example.dz7Api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.Duration;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "musica")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_musica")
    private Long idMusic;

    @NotNull(message = "A música deve ter um nome!")
    @Size(min = 1, message = "Nome inválido!")
    @Column(name = "nome_musica")
    private String musicName;

    @NotNull(message = "A música deve ter um link!")
    @Size(min = 1, message = "O link da música não pode ser vazio!")
    @Column(name = "link_musica")
    private String musicLink;
    
    @NotNull(message = "A música deve ter um tempo de duração!")
    @Column(name = "duracao_musica")
    private Duration musicDuration;

    @NotNull(message = "A música deve ter um gênero!")
    @Size(min = 1, message = "O gênero da música não pode ser vazio!")
    @Column(name = "genero_musica")
    private String musicGenre;

    @ManyToOne
    @JoinColumn(name = "idCategory", nullable = false)
    @NotNull(message = "A música deve possuir uma categoria!")
    @Column(name = "categoria_id_categoria")
    private Category musicCategory;

    // Pra gerar um construtor sem parametros pode-se usar o Lombok, conferir necessidade
    // @NoArgsConstructor
}

