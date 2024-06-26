package com.ddd.project.songnine.UsuarioDomain;

import java.util.Collection;
import java.util.UUID;

import com.ddd.project.songnine.CobrancaDomain.Assinatura;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @Min(8)
    @Max(16)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
    @NotBlank
    private String senha;
    @Min(11)
    @Max(11)
    @NotBlank
    private Integer telefone;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Endereco endereco;
    @OneToMany
    private Collection<Playlist> playlists;
    @OneToMany
    private Collection<Musica> musicasFavoritas;
    @OneToOne
    private Assinatura assinatura;
}
