package com.ddd.project.songnine.UsuarioDomain.Presentation.Model;

public record UsuarioRegisterRequestModel(
    String nome,
    String email,
    String senha,
    Integer telefone,
    EnderecoRegisterRequestModel endereco
) {

}
