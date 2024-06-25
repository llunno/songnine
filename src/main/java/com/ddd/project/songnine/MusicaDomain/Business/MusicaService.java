package com.ddd.project.songnine.MusicaDomain.Business;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddd.project.songnine.MusicaDomain.Musica;
import com.ddd.project.songnine.MusicaDomain.Repository.MusicaRepository;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    public Collection<Musica> getMusicas() {
        return musicaRepository.findAll();
    }

    public Musica getMusicaById(String id) {
        return musicaRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public Collection<Musica> getAllMusicas() {
        return musicaRepository.findAll();
    }
}
