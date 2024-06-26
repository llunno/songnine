package com.ddd.project.songnine.CobrancaDomain;

import java.time.LocalDate;
import java.util.UUID;

import com.ddd.project.songnine.Business.Services.Validators.NumeroCartao;
import com.ddd.project.songnine.CobrancaDomain.Constants.Bandeira;
import com.ddd.project.songnine.CobrancaDomain.Constants.TipoCartao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @NumeroCartao
    private Integer numero;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private TipoCartao tipo;
    @NotBlank
    private Integer cvv;
    private String titulo = "Sem titulo";
    @NotBlank
    private String nomeTitular;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Bandeira bandeira = Bandeira.DESCONHECIDA;
    @NotBlank
    private LocalDate validade;
    private boolean ativo = true;

    private Bandeira verifyBandeira() {
        var tipoBandeira = Bandeira.DESCONHECIDA;
        for (var bandeira : Bandeira.values()) {
            if (this.numero.toString().matches(bandeira.getRegex())) {
                tipoBandeira = Bandeira.valueOf(bandeira.name());
            }
            else {
                tipoBandeira = Bandeira.DESCONHECIDA;
            }
        }
        return tipoBandeira;
    }
}
