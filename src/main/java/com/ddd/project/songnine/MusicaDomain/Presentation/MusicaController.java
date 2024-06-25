package com.ddd.project.songnine.MusicaDomain.Presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddd.project.songnine.MusicaDomain.Playlist;
import com.ddd.project.songnine.MusicaDomain.Business.MusicaService;
import com.ddd.project.songnine.MusicaDomain.Business.PlaylistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/get")
    public ResponseEntity<?> getMusica(@RequestParam String Id) {
        return ResponseEntity.ok(musicaService.getMusicaById(Id));
    }
    
    @GetMapping("/getall")
    public ResponseEntity<?> getAllMusicas() {
        return ResponseEntity.ok(musicaService.getAllMusicas());
    }

    @GetMapping("/getAllPlaylists")
    public ResponseEntity<?> getPlaylists() {
        return ResponseEntity.ok(playlistService.getPlaylists());
    }

    @GetMapping("/addToPlaylist")
    public void addMusicaToPlaylist(String playlistId, String musicaId) {
        playlistService.addMusicaToPlaylist(playlistId, musicaId);
    }
    
    @PostMapping("/createPlaylist")
    public ResponseEntity<?> createPlaylist(@RequestBody Playlist playlistName) {
        Playlist playlist = playlistService.createPlaylist(playlistName);
        return ResponseEntity.ok(playlist);
    }
}
