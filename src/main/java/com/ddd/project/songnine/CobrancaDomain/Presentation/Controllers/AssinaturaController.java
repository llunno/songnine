package com.ddd.project.songnine.CobrancaDomain.Presentation.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddd.project.songnine.Business.Interfaces.CommomControllerMethods;
import com.ddd.project.songnine.CobrancaDomain.Assinatura;
import com.ddd.project.songnine.CobrancaDomain.Presentation.Model.CreateAssinaturaReqModel;
import com.ddd.project.songnine.CobrancaDomain.Services.AssinaturaService;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController implements CommomControllerMethods<CreateAssinaturaReqModel, UUID>{

    @Autowired
    private AssinaturaService assinaturaService;

    @Override
    public ResponseEntity<?> create(CreateAssinaturaReqModel registerRequest) {
        assinaturaService.criarAssinatura(registerRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getById(UUID id) {
        Assinatura assinatura = assinaturaService.getAssinatura(id);
        return ResponseEntity.ok(assinatura);
    }
}
