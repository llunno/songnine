package com.ddd.project.songnine.UsuarioDomain.Presentation.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddd.project.songnine.Business.Interfaces.CommomControllerMethods;
import com.ddd.project.songnine.UsuarioDomain.UsuarioMapper;
import com.ddd.project.songnine.UsuarioDomain.Presentation.Model.UsuarioRegisterRequestModel;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements CommomControllerMethods<UsuarioRegisterRequestModel, UUID>{

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public ResponseEntity<?> create(UsuarioRegisterRequestModel registerRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ResponseEntity<?> update(UUID id, UsuarioRegisterRequestModel registerRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ResponseEntity<?> getById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseEntity<?> delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ResponseEntity<?> deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public ResponseEntity<?> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}
