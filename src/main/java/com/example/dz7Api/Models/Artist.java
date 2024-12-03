package com.example.dz7Api.Models;

// java util
import java.util.HashSet;
import java.util.Set;

// jakarta
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;

// lombok
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "artist")
public class Artist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artist")
    private Long idArtist;

    @Column(name = "name_artist")
    @NotNull(message = "O artista deve ter um nome!")
    @Size(min = 1, message = "Nome inválido! Tente novamente.")
    private String artistName;

    @Column(name = "link_artist")
    @NotNull(message = "O artista deve ter um link para o perfil!")
    @Size(min = 1, message = "Link inválido! tente novamente.")
    private String artistProfileLink;

    @ManyToMany
    @JoinTable(
        name = "artist_has_music",
        joinColumns = @JoinColumn(name = "artist_id"),
        inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private Set<Music> artistMusics = new HashSet<>();
}
