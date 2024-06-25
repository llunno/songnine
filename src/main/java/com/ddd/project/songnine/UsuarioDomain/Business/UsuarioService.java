package com.ddd.project.songnine.UsuarioDomain.Business;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddd.project.songnine.Business.Interfaces.EntityPersistenceServiceMethods;
import com.ddd.project.songnine.MusicaDomain.Repository.MusicaRepository;
import com.ddd.project.songnine.UsuarioDomain.Usuario;
import com.ddd.project.songnine.UsuarioDomain.UsuarioMapper;
import com.ddd.project.songnine.UsuarioDomain.Presentation.Model.UsuarioRegisterRequestModel;
import com.ddd.project.songnine.UsuarioDomain.Repository.UsuarioRepository;

@Service
public class UsuarioService implements EntityPersistenceServiceMethods<UsuarioRegisterRequestModel, Usuario>{

    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MusicaRepository musicaRepository;

    @Override
    public Usuario saveEntity(UsuarioRegisterRequestModel request) {
        return usuarioRepository.save(usuarioMapper.fromRegisterReqTUsuario(request));
    }

    public void favoritarMusica(UUID usuario, UUID musica) {
        Usuario usuarioEntity = usuarioRepository.findById(usuario).orElse(null);
        usuarioEntity.getMusicasFavoritas().add(musicaRepository.findById(musica).orElse(null));
        usuarioRepository.save(usuarioEntity);    
    }

    public Usuario getUsuarioById(UUID id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
