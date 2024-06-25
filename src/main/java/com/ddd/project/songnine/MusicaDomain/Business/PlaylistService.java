package com.ddd.project.songnine.MusicaDomain.Business;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddd.project.songnine.MusicaDomain.Playlist;
import com.ddd.project.songnine.MusicaDomain.Repository.MusicaRepository;
import com.ddd.project.songnine.MusicaDomain.Repository.PlaylistRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MusicaRepository musicaRepository;

    public void addMusicaToPlaylist(String playlistId, String musicaId) {
        playlistRepository.findById(UUID.fromString(playlistId)).get().getMusicas().add(musicaRepository.findById(UUID.fromString(musicaId)).get());
        playlistRepository.save(playlistRepository.findById(UUID.fromString(playlistId)).get());
    }

    public Collection<Playlist> getPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(String id) {
        return playlistRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }
}
