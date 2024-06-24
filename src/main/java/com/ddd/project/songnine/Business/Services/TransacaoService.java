package com.ddd.project.songnine.Business.Services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddd.project.songnine.Business.EntityCreationServiceMethods;
import com.ddd.project.songnine.Business.Exceptions.TransacaoReprovadaException;
import com.ddd.project.songnine.CobrancaDomain.Transacao;
import com.ddd.project.songnine.CobrancaDomain.Repository.TransacaoRepository;

@Service
public class TransacaoService implements EntityCreationServiceMethods<Transacao, Transacao>{

    @Autowired
    private TransacaoRepository transacaoRepository;

    private int obterQuantidadeTransacoesSemelhantesNoIntervalo(Transacao transacao, int intervalo) {
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

    private boolean transacaoAprovadaParaSerSalva(Transacao transacao) {
        int intervaloPorTransacoesEmMinutos = Transacao.getIntervaloPorTransacoes();
        if (obterQuantidadeTransacoesTotaisNoIntervalo(intervaloPorTransacoesEmMinutos) >= Transacao.getMaxTransacoesPorIntervalo()) {
            return false;
        } else if (obterQuantidadeTransacoesSemelhantesNoIntervalo(transacao, intervaloPorTransacoesEmMinutos) >= Transacao.getMaxTransacoesSemelhantesPorIntervalo()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Transacao createEntity(Transacao transacao) {
        if (transacaoAprovadaParaSerSalva(transacao)) {
            transacao.setAprovada(true);
            return transacaoRepository.save(transacao);
        } else {
            throw new TransacaoReprovadaException("Limite de transações excedido. Tente novamente mais tarde.");
        }
    }
}
