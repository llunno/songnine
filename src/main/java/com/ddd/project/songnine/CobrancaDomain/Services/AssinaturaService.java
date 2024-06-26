package com.ddd.project.songnine.CobrancaDomain.Services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ddd.project.songnine.CobrancaDomain.Assinatura;
import com.ddd.project.songnine.CobrancaDomain.Cartao;
import com.ddd.project.songnine.CobrancaDomain.Plano;
import com.ddd.project.songnine.CobrancaDomain.TransacaoAggregate;
import com.ddd.project.songnine.CobrancaDomain.Presentation.Model.CreateAssinaturaReqModel;
import com.ddd.project.songnine.CobrancaDomain.Repository.AssinaturaRepository;
import com.ddd.project.songnine.CobrancaDomain.Repository.CartaoRepository;
import com.ddd.project.songnine.CobrancaDomain.Repository.PlanoRepository;
import com.ddd.project.songnine.UsuarioDomain.Usuario;
import com.ddd.project.songnine.UsuarioDomain.Repository.UsuarioRepository;

@Service
public class AssinaturaService {
    
    @Autowired
    private AssinaturaRepository assinaturaRepository;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private PlanoRepository planoRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    public void criarAssinatura(CreateAssinaturaReqModel reqModel) {
        var usuario = userRepository.findById(UUID.fromString(reqModel.usuarioId())).orElse(null);
        var plano = planoRepository.findById(UUID.fromString(reqModel.planoId())).orElse(null);
        var cartao = cartaoRepository.findById(UUID.fromString(reqModel.cartaoId())).orElse(null);
        
        var assinatura = Assinatura.criar(usuario, plano, cartao);
        assinaturaRepository.save(assinatura);
        criarTransacaoEvent(assinatura);
    }

    public Collection<Assinatura> getAssinaturas() {
        return assinaturaRepository.findAll();
    }

    public Assinatura getAssinatura(UUID id) {
        return assinaturaRepository.findById(id).orElse(null);
    }

    public void criarTransacaoEvent(Assinatura assinatura) {
        var transacao = TransacaoAggregate.builder()
            .valor(assinatura.getPlano().getValor())
            .descricao("Assinatura de " + assinatura.getPlano().getNome())
            .data(LocalDateTime.now())
            .cartao(assinatura.getCartao())
            .servico(assinatura)
            .usuario(assinatura.getUsuario())
            .applicationEventPublisher(eventPublisher)
            .build();

            transacao.publishCriarTransacaoEvent();
    }
}
