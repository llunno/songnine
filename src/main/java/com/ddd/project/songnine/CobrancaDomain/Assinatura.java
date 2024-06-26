package com.ddd.project.songnine.CobrancaDomain;

import java.time.LocalDateTime;

import com.ddd.project.songnine.UsuarioDomain.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "assinaturas")
public class Assinatura extends Servico  {

    @ManyToOne
    private Plano plano;
    @OneToOne(mappedBy = "assinatura")
    private Usuario usuario;
    @ManyToOne
    @NotBlank
    private Cartao cartao;
    private boolean ativo = true;

    public static Assinatura criar(Usuario usuario, Plano plano, Cartao cartao) {
        var assinatura = new Assinatura();
        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);
        assinatura.setCartao(cartao);
        assinatura.setNome("Assinatura de " + plano.getNome());
        assinatura.setDescricao("Assinatura de " + plano.getNome() + " para o usuário " + usuario.getNome());
        
        return assinatura;
    }
}
