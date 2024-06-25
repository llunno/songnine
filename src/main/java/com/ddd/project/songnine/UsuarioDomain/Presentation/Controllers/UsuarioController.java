package com.ddd.project.songnine.UsuarioDomain.Presentation.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddd.project.songnine.Business.Interfaces.CommomControllerMethods;
import com.ddd.project.songnine.UsuarioDomain.Business.UsuarioService;
import com.ddd.project.songnine.UsuarioDomain.Presentation.Model.UsuarioRegisterRequestModel;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements CommomControllerMethods<UsuarioRegisterRequestModel, UUID>{

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ResponseEntity<?> create(UsuarioRegisterRequestModel registerRequest) {
        usuarioService.saveEntity(registerRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favoritarMusica")
    public ResponseEntity<?> favoritarMusica(@RequestParam UUID usuario, @RequestParam UUID musica) {
        usuarioService.favoritarMusica(usuario, musica);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getById(UUID id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }
}
