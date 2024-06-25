package com.ddd.project.songnine.UsuarioDomain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ddd.project.songnine.UsuarioDomain.Presentation.Model.EnderecoRegisterRequestModel;
import com.ddd.project.songnine.UsuarioDomain.Presentation.Model.UsuarioRegisterRequestModel;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assinatura", ignore = true)
    @Mapping(target = "musicasFavoritas", ignore = true)
    @Mapping(target = "playlists", ignore = true)
    public Usuario fromRegisterReqTUsuario(UsuarioRegisterRequestModel usuarioRegisterRequestModel);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Endereco fromEnderecoRegisterReqToEndereco(EnderecoRegisterRequestModel enderecoRegisterRequestModel);
}
