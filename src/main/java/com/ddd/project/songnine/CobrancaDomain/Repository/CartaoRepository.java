package com.ddd.project.songnine.CobrancaDomain.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddd.project.songnine.CobrancaDomain.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, UUID>{

}
