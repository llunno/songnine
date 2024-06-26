package com.ddd.project.songnine;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import com.ddd.project.songnine.CobrancaDomain.Assinatura;
import com.ddd.project.songnine.CobrancaDomain.Cartao;
import com.ddd.project.songnine.CobrancaDomain.Plano;
import com.ddd.project.songnine.CobrancaDomain.TransacaoAggregate;
import com.ddd.project.songnine.CobrancaDomain.Presentation.Controllers.AssinaturaController;
import com.ddd.project.songnine.CobrancaDomain.Presentation.Model.CreateAssinaturaReqModel;
import com.ddd.project.songnine.CobrancaDomain.Repository.CartaoRepository;
import com.ddd.project.songnine.CobrancaDomain.Repository.PlanoRepository;
import com.ddd.project.songnine.CobrancaDomain.Repository.TransacaoRepository;
import com.ddd.project.songnine.CobrancaDomain.Repository.AssinaturaRepository;
import com.ddd.project.songnine.CobrancaDomain.Services.AssinaturaService;
import com.ddd.project.songnine.CobrancaDomain.Services.TransacaoService;
import com.ddd.project.songnine.UsuarioDomain.Usuario;
import com.ddd.project.songnine.UsuarioDomain.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AssinaturaControllerTest {

    @Mock
    private AssinaturaService assinaturaServiceMock;
    @Mock
    private UsuarioRepository usuarioRepositoryMock;
    @Mock
    private PlanoRepository planoRepositoryMock;
    @Mock
    private AssinaturaRepository assinaturaRepositoryMock;
    @Mock
    private CartaoRepository cartaoRepositoryMock;
    @Mock
    private TransacaoRepository transacaoRepositoryMock;
    @Autowired
    private AssinaturaService assinaturaServiceRealImpl;
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreate() throws Exception {
        var userId = UUID.randomUUID().toString();
        var planoId = UUID.randomUUID().toString();
        var cartaoId = UUID.randomUUID().toString();

        Usuario usuario = Instancio.of(Usuario.class).set(Select.field("id"), UUID.fromString(userId)).create();
        Cartao cartao = Instancio.of(Cartao.class)
        .set(Select.field("id"), UUID.fromString(cartaoId)) 
        .set(Select.field("ativo"), true)
        .create();
        Plano plano = Instancio.of(Plano.class).set(Select.field("id"), UUID.fromString(planoId)).create();

        CreateAssinaturaReqModel registerRequest = Instancio.of(CreateAssinaturaReqModel.class)
            .set(Select.field("usuarioId"), userId)
            .set(Select.field("planoId"), planoId)
            .set(Select.field("cartaoId"), cartaoId)
            .create();

        when(usuarioRepositoryMock.findById(UUID.fromString(userId))).thenReturn(Optional.of(usuario));
        when(planoRepositoryMock.findById(UUID.fromString(planoId))).thenReturn(Optional.of(plano));
        when(cartaoRepositoryMock.findById(UUID.fromString(cartaoId))).thenReturn(Optional.of(cartao));
        when(transacaoRepositoryMock.save(any(TransacaoAggregate.class))).thenReturn(Instancio.create(TransacaoAggregate.class));

        ReflectionTestUtils.setField(assinaturaServiceRealImpl, "userRepository", usuarioRepositoryMock);
        ReflectionTestUtils.setField(assinaturaServiceRealImpl, "planoRepository", planoRepositoryMock);
        ReflectionTestUtils.setField(assinaturaServiceRealImpl, "cartaoRepository", cartaoRepositoryMock);
        ReflectionTestUtils.setField(assinaturaServiceRealImpl, "assinaturaRepository", assinaturaRepositoryMock);
        ReflectionTestUtils.setField(transacaoService, "transacaoRepository", transacaoRepositoryMock);

        mockMvc.perform(post("/assinaturas/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(registerRequest)))
            .andExpect(status().isOk());

    }

    @Test
    public void testGetById() throws Exception {
        UUID assinaturaId = UUID.randomUUID();
        Assinatura assinatura = Instancio.create(Assinatura.class);

        when(assinaturaServiceMock.getAssinatura(assinaturaId)).thenReturn(assinatura);

        mockMvc.perform(get("/assinaturas/get?id=" + assinaturaId.toString()))
                .andExpect(status().isOk());
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