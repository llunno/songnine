package com.ddd.project.songnine.CobrancaDomain.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ddd.project.songnine.CobrancaDomain.TransacaoAggregate;
import com.ddd.project.songnine.UsuarioDomain.Usuario;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoAggregate, UUID>{

    @Query("SELECT COUNT(t) FROM TransacaoAggregate t WHERE t.valor = :valor AND t.usuario = :usuario AND t.data BETWEEN :doisMinutosAtras AND :agora")
    int getQuantByValorAndUsuarioAndData(
        @Param("valor") Double valor,
        @Param("usuario") Usuario usuario,
        @Param("doisMinutosAtras") LocalDateTime doisMinutosAtras,
        @Param("agora") LocalDateTime agora
    );

    @Query("SELECT COUNT(t) FROM TransacaoAggregate t WHERE t.data BETWEEN :doisMinutosAtras AND :agora")
    int getQuantByData(@Param("doisMinutosAtras") LocalDateTime doisMinutosAtras, @Param("agora") LocalDateTime agora);
}
