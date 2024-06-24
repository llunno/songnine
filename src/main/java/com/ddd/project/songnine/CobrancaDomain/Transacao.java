package com.ddd.project.songnine.CobrancaDomain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ddd.project.songnine.Business.Exceptions.TransacaoReprovadaException;
import com.ddd.project.songnine.UsuarioDomain.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;
    @NotBlank
    private Double valor;
    private String descricao;
    @NotBlank
    private LocalDateTime data;
    @ManyToOne
    @NotBlank
    private Cartao cartao;
    private boolean aprovada;
    @ManyToOne
    private Servico servico;
    @ManyToOne
    private Usuario usuario;

    private static final int INTERVALO_POR_TRANSACOES = 2;
    private static final int MAX_TRANSACOES_POR_INTERVALO = 3;
    private static final int MAX_TRANSACOES_SEMELHANTES_POR_INTERVALO = 2;
    
    
    private void assertTransacaoUserEqualsServicoUser() {
        if (this.servico instanceof Assinatura) {
            if (!this.usuario.equals(((Assinatura) this.servico).getUsuario())) {
                throw new TransacaoReprovadaException("Transação não pertence ao usuário");
            }
        }
    }
    
    public static int getMaxTransacoesSemelhantesPorIntervalo() {
        return MAX_TRANSACOES_SEMELHANTES_POR_INTERVALO;
    }

    public static int getIntervaloPorTransacoes() {
        return INTERVALO_POR_TRANSACOES;
    }

    public static int getMaxTransacoesPorIntervalo() {
        return MAX_TRANSACOES_POR_INTERVALO;
    }

    public void setAprovada(boolean aprovada) {
        if (!this.cartao.isAtivo()) {
            this.aprovada = false;
            this.descricao = "Cartão inativo";
            throw new TransacaoReprovadaException("Cartão inativo");
        } else {
            this.aprovada = aprovada;
        }
    }

    public void setUsuario(Usuario usuario) {
        assertTransacaoUserEqualsServicoUser();
        this.usuario = usuario;
    }
}
