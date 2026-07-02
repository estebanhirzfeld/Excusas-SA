package ar.edu.davinci.excusassa.controller;

import ar.edu.davinci.excusassa.dto.EncargadoRequestDTO;
import ar.edu.davinci.excusassa.dto.EncargadoResponseDTO;
import ar.edu.davinci.excusassa.dto.ModoRequestDTO;
import ar.edu.davinci.excusassa.service.EncargadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encargados")
public class EncargadoController {

    private final EncargadoService service;

    public EncargadoController(EncargadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EncargadoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarEncargados());
    }

    @PostMapping
    public ResponseEntity<EncargadoResponseDTO> agregar(
            @Valid @RequestBody EncargadoRequestDTO dto) {
        EncargadoResponseDTO creado = service.agregarEncargado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/modo")
    public ResponseEntity<EncargadoResponseDTO> cambiarModo(
            @RequestParam int nroLegajo,
            @Valid @RequestBody ModoRequestDTO dto) {
        EncargadoResponseDTO actualizado = service.cambiarModo(nroLegajo, dto.getModo());
        return ResponseEntity.ok(actualizado);
    }
}
