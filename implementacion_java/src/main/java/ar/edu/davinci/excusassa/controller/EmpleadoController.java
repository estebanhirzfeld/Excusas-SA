package ar.edu.davinci.excusassa.controller;

import ar.edu.davinci.excusassa.dto.EmpleadoRequestDTO;
import ar.edu.davinci.excusassa.dto.EmpleadoResponseDTO;
import ar.edu.davinci.excusassa.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> crear(@Valid @RequestBody EmpleadoRequestDTO dto) {
        EmpleadoResponseDTO creado = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
