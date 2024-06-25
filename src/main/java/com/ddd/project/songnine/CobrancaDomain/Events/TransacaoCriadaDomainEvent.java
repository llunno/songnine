package com.ddd.project.songnine.CobrancaDomain.Events;

import com.ddd.project.songnine.Business.Interfaces.AbstractDomainEvent;
import com.ddd.project.songnine.CobrancaDomain.TransacaoAggregate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoCriadaDomainEvent  extends AbstractDomainEvent{
    private TransacaoAggregate transacao;
}
