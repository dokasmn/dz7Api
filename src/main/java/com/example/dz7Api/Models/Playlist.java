package com.example.dz7Api.Models;

// java util
import java.util.HashSet;
import java.util.Set;

// jakarta
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

// lombok
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_playlist")
    private Long idPlaylist;

    @NotNull(message = "A playlist deve ter um nome!")
    @Size(min = 1, message = "Nome inválido! Tente novamente")
    @Column(name = "name_playlist")
    private String playlistName;

    @ManyToMany
    @JoinTable(
        name = "playlist_has_music",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private Set<Music> musics = new HashSet<>();
}
