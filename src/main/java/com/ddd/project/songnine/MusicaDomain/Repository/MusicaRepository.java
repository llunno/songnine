package com.ddd.project.songnine.MusicaDomain.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddd.project.songnine.MusicaDomain.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, UUID>{

}
