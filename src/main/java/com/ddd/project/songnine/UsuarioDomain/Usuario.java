package com.ddd.project.songnine.UsuarioDomain;

import java.util.Collection;
import java.util.UUID;

import com.ddd.project.songnine.MusicaDomain.Musica;
import com.ddd.project.songnine.MusicaDomain.Playlist;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private Integer telefone;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Endereco endereco;
    @OneToMany(mappedBy = "usuario")
    private Collection<Playlist> playlists;
    @OneToMany
    private Collection<Musica> musicasFavoritas;
}
