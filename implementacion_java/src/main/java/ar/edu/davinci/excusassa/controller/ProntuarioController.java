package ar.edu.davinci.excusassa.controller;

import ar.edu.davinci.excusassa.dto.ProntuarioResponseDTO;
import ar.edu.davinci.excusassa.service.ProntuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    private final ProntuarioService service;

    public ProntuarioController(ProntuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProntuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
