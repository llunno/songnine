package com.ddd.project.songnine.CobrancaDomain;

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
public class Assinatura extends Servico {

    @ManyToOne
    private Plano plano;
    @OneToOne(mappedBy = "assinatura")
    private Usuario usuario;
    @ManyToOne
    @NotBlank
    private Cartao cartao;
    private boolean ativo = true;
}
