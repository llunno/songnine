package com.ddd.project.songnine.Business.Interfaces;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ddd.project.songnine.Business.Constants.EventStatus;
import com.ddd.project.songnine.Business.Constants.EventType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDomainEvent {

    private UUID id = UUID.randomUUID();
    private EventStatus status;
    private EventType nome;
    private String observacao;
    private LocalDateTime dataInicio = LocalDateTime.now();
}
