package com.ddd.project.songnine.UsuarioDomain.Presentation.Model;

import com.ddd.project.songnine.UsuarioDomain.Estado;

public record EnderecoRegisterRequestModel(
    String cep,
    String logradouro,
    String bairro,
    String cidade,
    Estado estado,
    String complemento,
    String numero,
    String pais
) {
}
