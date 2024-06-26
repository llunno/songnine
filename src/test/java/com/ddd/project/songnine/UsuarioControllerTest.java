package com.ddd.project.songnine;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.ddd.project.songnine.MusicaDomain.Musica;
import com.ddd.project.songnine.MusicaDomain.Repository.MusicaRepository;
import com.ddd.project.songnine.UsuarioDomain.Usuario;
import com.ddd.project.songnine.UsuarioDomain.Business.UsuarioService;
import com.ddd.project.songnine.UsuarioDomain.Presentation.Controllers.UsuarioController;
import com.ddd.project.songnine.UsuarioDomain.Presentation.Model.UsuarioRegisterRequestModel;
import com.ddd.project.songnine.UsuarioDomain.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioServiceMock;
    @Mock
    private UsuarioRepository usuarioRepositoryMock;
    @Mock
    private MusicaRepository musicaRepositoryMock;
    @Autowired
    private UsuarioService usuarioServiceRealImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UsuarioController controller;

    @Test
    public void testCreate() throws Exception {
        UsuarioRegisterRequestModel registerRequest = Instancio.create(UsuarioRegisterRequestModel.class);

        when(usuarioServiceMock.saveEntity(registerRequest)).thenReturn(Instancio.create(Usuario.class));
        ReflectionTestUtils.setField(controller, "usuarioService", usuarioServiceMock);

        mockMvc.perform(post("/usuarios/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(registerRequest)))
            .andExpect(status().isOk());

        verify(usuarioServiceMock).saveEntity(registerRequest);
    }

    @Test
    public void testFavoritarMusica() throws Exception {

        UUID usuarioId = UUID.randomUUID();
        UUID musicaId = UUID.randomUUID();

        var musica = Instancio.of(Musica.class).set(Select.field("id"), musicaId).create();
        var usuario = Instancio.of(Usuario.class).set(Select.field("id"), usuarioId).create();
        
        when(usuarioRepositoryMock.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(musicaRepositoryMock.findById(musicaId)).thenReturn(Optional.of(musica));

        ReflectionTestUtils.setField(usuarioServiceRealImpl, "usuarioRepository", usuarioRepositoryMock);
        ReflectionTestUtils.setField(usuarioServiceRealImpl, "musicaRepository", musicaRepositoryMock);

        mockMvc.perform(get("/usuarios/favoritarMusica")
                .param("usuario", usuarioId.toString())
                .param("musica", musicaId.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        UUID usuarioId = UUID.randomUUID();

        when(usuarioServiceMock.getUsuarioById(usuarioId)).thenReturn(Instancio.create(Usuario.class));
        
        ReflectionTestUtils.setField(controller, "usuarioService", usuarioServiceMock);

        mockMvc.perform(get("/usuarios/get?id=" + usuarioId))
                .andExpect(status().isOk());

        verify(usuarioServiceMock).getUsuarioById(usuarioId);
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