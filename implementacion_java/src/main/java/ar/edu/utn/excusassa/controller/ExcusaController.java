package ar.edu.utn.excusassa.controller;

import ar.edu.utn.excusassa.dto.ExcusaRequestDTO;
import ar.edu.utn.excusassa.dto.ExcusaResponseDTO;
import ar.edu.utn.excusassa.service.ExcusaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excusas")
public class ExcusaController {

    private final ExcusaService service;

    public ExcusaController(ExcusaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExcusaResponseDTO> registrar(@Valid @RequestBody ExcusaRequestDTO dto) {
        ExcusaResponseDTO creada = service.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<ExcusaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<List<ExcusaResponseDTO>> listarPorLegajo(@PathVariable int legajo) {
        return ResponseEntity.ok(service.listarPorLegajo(legajo));
    }

    @GetMapping("/rechazadas")
    public ResponseEntity<List<ExcusaResponseDTO>> listarRechazadas() {
        return ResponseEntity.ok(service.listarRechazadas());
    }

    @GetMapping("/busqueda")
    public ResponseEntity<List<ExcusaResponseDTO>> buscar(
            @RequestParam int legajo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        return ResponseEntity.ok(service.buscar(legajo, fechaDesde, fechaHasta));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLimite) {

        if (fechaLimite == null) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "El parámetro fechaLimite es obligatorio para eliminar excusas"));
        }

        long eliminadas = service.eliminarAnterioresA(fechaLimite);
        return ResponseEntity.ok(Map.of("excusasEliminadas", eliminadas));
    }
}
