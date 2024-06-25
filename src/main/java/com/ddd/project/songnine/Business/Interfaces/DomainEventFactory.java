package com.ddd.project.songnine.Business.Interfaces;

import com.ddd.project.songnine.Business.Constants.EventType;
import com.ddd.project.songnine.CobrancaDomain.Events.TransacaoCriadaDomainEvent;
import com.ddd.project.songnine.MusicaDomain.Events.MusicaFavoritadaDomainEvent;

public class DomainEventFactory {

    public static AbstractDomainEvent createDomainEvent(EventType eventType) {
        switch (eventType) {
            case TRANSACAO:
                return new TransacaoCriadaDomainEvent();
            case FAVORITAR_MUSICA:
                return new MusicaFavoritadaDomainEvent();
            default:
                return null;
        }
    }
}
