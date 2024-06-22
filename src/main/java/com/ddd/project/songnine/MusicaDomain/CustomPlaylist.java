package com.ddd.project.songnine.MusicaDomain;

import com.ddd.project.songnine.UsuarioDomain.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "playlists_customizadas")
public class CustomPlaylist extends Playlist {
    @ManyToOne
    private Usuario usuario;
}
