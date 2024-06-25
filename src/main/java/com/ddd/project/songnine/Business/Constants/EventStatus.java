package com.ddd.project.songnine.Business.Constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventStatus {

    INICIADO("Iniciado"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado"),
    EM_ANDAMENTO("Em andamento"),
    AGENDADO("Agendado"),
    PAUSADO("Pausado");

    public String status;
}
