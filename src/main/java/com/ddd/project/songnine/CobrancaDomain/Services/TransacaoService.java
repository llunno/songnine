package com.ddd.project.songnine.CobrancaDomain.Services;

import java.time.LocalDateTime;

import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ddd.project.songnine.Business.Exceptions.TransacaoReprovadaException;
import com.ddd.project.songnine.Business.Interfaces.EntityPersistenceServiceMethods;
import com.ddd.project.songnine.CobrancaDomain.TransacaoAggregate;
import com.ddd.project.songnine.CobrancaDomain.Events.TransacaoCriadaDomainEvent;
import com.ddd.project.songnine.CobrancaDomain.Repository.TransacaoRepository;

@Service
public class TransacaoService implements EntityPersistenceServiceMethods<TransacaoCriadaDomainEvent, TransacaoAggregate>{

    @Autowired
    private TransacaoRepository transacaoRepository;

    private int obterQuantidadeTransacoesSemelhantesNoIntervalo(TransacaoAggregate transacao, int intervalo) {
        LocalDateTime doisMinutosAtras = LocalDateTime.now().minusMinutes(intervalo);
        LocalDateTime agora = LocalDateTime.now();
        
        int quantTransacoesSemelhantesNoIntervalo = transacaoRepository.getQuantByValorAndUsuarioAndData(
            transacao.getValor(),
            transacao.getUsuario(),
            doisMinutosAtras,
            agora);

        return quantTransacoesSemelhantesNoIntervalo;
    }

    private int obterQuantidadeTransacoesTotaisNoIntervalo(int intervalo) {
        LocalDateTime doisMinutosAtras = LocalDateTime.now().minusMinutes(intervalo);
        LocalDateTime agora = LocalDateTime.now();

        int quantTransacoesTotaisNoIntervalo = transacaoRepository.getQuantByData(
            doisMinutosAtras,
            agora);

        return quantTransacoesTotaisNoIntervalo;
    }

    private boolean transacaoAprovadaParaSerSalva(TransacaoAggregate transacao) {
        int intervaloPorTransacoesEmMinutos = TransacaoAggregate.getIntervaloPorTransacoes();
        if (obterQuantidadeTransacoesTotaisNoIntervalo(intervaloPorTransacoesEmMinutos) >= TransacaoAggregate.getMaxTransacoesPorIntervalo()) {
            return false;
        } else if (obterQuantidadeTransacoesSemelhantesNoIntervalo(transacao, intervaloPorTransacoesEmMinutos) >= TransacaoAggregate.getMaxTransacoesSemelhantesPorIntervalo()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, classes = TransacaoCriadaDomainEvent.class)
    public TransacaoAggregate saveEntity(TransacaoCriadaDomainEvent event) {
        if (transacaoAprovadaParaSerSalva(event.getTransacao())) {
            event.getTransacao().setAprovada(true);
            return transacaoRepository.save(event.getTransacao());
        } else {
            throw new TransacaoReprovadaException("Limite de transações excedido. Tente novamente mais tarde.");
        }
    }
}
