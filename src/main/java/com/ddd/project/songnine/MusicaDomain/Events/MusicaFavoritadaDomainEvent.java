package com.ddd.project.songnine.MusicaDomain.Events;

import com.ddd.project.songnine.Business.Interfaces.AbstractDomainEvent;
import com.ddd.project.songnine.CobrancaDomain.TransacaoAggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MusicaFavoritadaDomainEvent extends AbstractDomainEvent {

    private TransacaoAggregate transacao;
}
