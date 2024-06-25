package com.ddd.project.songnine.Business.Constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventType {
    TRANSACAO("Transação"),
    CADASTRO("Cadastro"),
    ATUALIZACAO_CADASTRO("Atualização de cadastro"),
    EXCLUSAO_CADASTRO("Exclusão de cadastro"),
    CONSULTA("Consulta"),
    FAVORITAR_MUSICA("Favoritar música"),
    CRIAR_PLAYLIST("Criar playlist");

    public String nome;
}
