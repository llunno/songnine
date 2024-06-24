package com.ddd.project.songnine.CobrancaDomain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ddd.project.songnine.UsuarioDomain.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    
    public void setAprovada(boolean aprovada) {
        if (!this.cartao.isAtivo()) {
            this.aprovada = false;
            this.descricao = "Cartão inativo";
        } else {
            this.aprovada = aprovada;
        }
    }

    public static class Builder {
        private UUID codigo;
        private Double valor;
        private String descricao;
        private LocalDateTime data;
        private Cartao cartao;
        private boolean aprovada;
        private Servico servico;
        private Usuario usuario;
    
        public Builder codigo(UUID codigo) {
            this.codigo = codigo;
            return this;
        }
    
        public Builder valor(Double valor) {
            this.valor = valor;
            return this;
        }
    
        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }
    
        public Builder data(LocalDateTime data) {
            this.data = data;
            return this;
        }
    
        public Builder cartao(Cartao cartao) {
            this.cartao = cartao;
            return this;
        }
    
        public Builder aprovada(boolean aprovada) {
            this.aprovada = aprovada;
            return this;
        }
    
        public Builder servico(Servico servico) {
            this.servico = servico;
            return this;
        }
    
        public Builder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }
    
        public Transacao build() {
            Transacao transacao = new Transacao();
            transacao.setCodigo(codigo);
            transacao.setValor(valor);
            transacao.setDescricao(descricao);
            transacao.setData(data);
            transacao.setCartao(cartao);
            transacao.setAprovada(aprovada);
            transacao.setServico(servico);
            transacao.setUsuario(usuario);
            return transacao;
        }
    }
}
