package com.ddd.project.songnine;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ddd.project.songnine.MusicaDomain.Business.MusicaService;
import com.ddd.project.songnine.MusicaDomain.Business.PlaylistService;
import com.ddd.project.songnine.MusicaDomain.Presentation.MusicaController;
import com.ddd.project.songnine.MusicaDomain.Repository.MusicaRepository;
import com.ddd.project.songnine.MusicaDomain.Repository.PlaylistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ddd.project.songnine.MusicaDomain.Musica;
import com.ddd.project.songnine.MusicaDomain.Playlist;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class MusicaControllerTest {

    @Mock
    private MusicaService musicaServiceMock;
    @Mock
    private PlaylistService playlistServiceMock;
    @Mock
    private MusicaRepository musicaRepositoryMock;
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @Autowired
    private PlaylistService playlistServiceRealImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MusicaController controller;

    @Test
    public void testGetMusica() throws Exception {
        String musicaId = UUID.randomUUID().toString();

        var music = Instancio.of(Musica.class).set(Select.field("id"), UUID.fromString(musicaId)).create();

        when(musicaServiceMock.getMusicaById(musicaId)).thenReturn(music);

        when(musicaServiceMock.getMusicaById(musicaId)).thenReturn(new Musica());

        ReflectionTestUtils.setField(controller, "musicaService", musicaServiceMock);

        mockMvc.perform(get("/musicas/get")
                .param("Id", musicaId))
                .andExpect(status().isOk());

        verify(musicaServiceMock).getMusicaById(musicaId);
    }

    @Test
    public void testGetAllMusicas() throws Exception {
        List<Musica> musicas = new ArrayList<>();

        when(musicaServiceMock.getAllMusicas()).thenReturn(musicas);

        ReflectionTestUtils.setField(controller, "musicaService", musicaServiceMock);

        mockMvc.perform(get("/musicas/getall"))
                .andExpect(status().isOk());

        verify(musicaServiceMock).getAllMusicas();
    }

    @Test
    public void testGetPlaylists() throws Exception {
        List<Playlist> playlists = new ArrayList<>();

        when(playlistServiceMock.getPlaylists()).thenReturn(playlists);

        ReflectionTestUtils.setField(controller, "playlistService", playlistServiceMock);

        mockMvc.perform(get("/musicas/getAllPlaylists"))
                .andExpect(status().isOk());

        verify(playlistServiceMock).getPlaylists();
    }

    @Test
    public void testAddMusicaToPlaylist() throws Exception {
        String playlistId = UUID.randomUUID().toString();
        String musicaId = UUID.randomUUID().toString();

        var playlist = Instancio.of(Playlist.class).set(Select.field("id"), UUID.fromString(playlistId)).create();
        var musica = Instancio.of(Musica.class).set(Select.field("id"), UUID.fromString(musicaId)).create();

        ReflectionTestUtils.setField(playlistServiceRealImpl, "playlistRepository", playlistRepositoryMock);
        ReflectionTestUtils.setField(playlistServiceRealImpl, "musicaRepository", musicaRepositoryMock);

        when(playlistRepositoryMock.findById(UUID.fromString(playlistId))).thenReturn(Optional.of(playlist));
        when(musicaRepositoryMock.findById(UUID.fromString(musicaId))).thenReturn(Optional.of(musica));

        mockMvc.perform(get("/musicas/addToPlaylist")
                .param("playlistId", playlistId)
                .param("musicaId", musicaId))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreatePlaylist() throws Exception {
        Playlist playlist = new Playlist();

        when(playlistServiceMock.createPlaylist(playlist)).thenReturn(playlist);

        ReflectionTestUtils.setField(controller, "playlistService", playlistServiceMock);

        mockMvc.perform(post("/musicas/createPlaylist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(playlist)))
                .andExpect(status().isOk());

        verify(playlistServiceMock).createPlaylist(any(Playlist.class));
    }

    // Helper method to convert an object to JSON string
    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}