package ar.edu.davinci.excusassa.service;

import ar.edu.davinci.excusassa.dto.ExcusaRequestDTO;
import ar.edu.davinci.excusassa.dto.ExcusaResponseDTO;
import ar.edu.davinci.excusassa.evaluacion.Responsable;
import ar.edu.davinci.excusassa.mapper.ExcusaMapper;
import ar.edu.davinci.excusassa.modelo.Empleado;
import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.Motivo;
import ar.edu.davinci.excusassa.modelo.motivo.TipoMotivo;
import ar.edu.davinci.excusassa.repository.ExcusaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExcusaService {

    private final ExcusaRepository repository;
    private final EmpleadoService empleadoService;
    private final ExcusaMapper mapper;
    private final Responsable cadenaDeResponsables;

    public ExcusaService(ExcusaRepository repository,
                          EmpleadoService empleadoService,
                          ExcusaMapper mapper,
                          Responsable cadenaDeResponsables) {
        this.repository = repository;
        this.empleadoService = empleadoService;
        this.mapper = mapper;
        this.cadenaDeResponsables = cadenaDeResponsables;
    }

    @Transactional
    public ExcusaResponseDTO registrar(ExcusaRequestDTO dto) {
        TipoMotivo tipoMotivo = parsearMotivo(dto.getMotivo());
        Motivo motivo = tipoMotivo.crearMotivo();

        Empleado empleado = empleadoService.buscarPorLegajo(dto.getLegajoEmpleado());
        Excusa excusa = new Excusa(empleado, motivo);

        cadenaDeResponsables.revisarExcusa(excusa);

        Excusa guardada = repository.save(excusa);
        return mapper.toDTO(guardada);
    }

    public List<ExcusaResponseDTO> listarTodas() {
        return repository.findAll().stream()
            .map(mapper::toDTO)
            .toList();
    }

    public List<ExcusaResponseDTO> listarPorLegajo(int legajo) {
        return repository.findByEmpleadoNroLegajo(legajo).stream()
            .map(mapper::toDTO)
            .toList();
    }

    public List<ExcusaResponseDTO> listarRechazadas() {
        return repository.findByAceptadaFalse().stream()
            .map(mapper::toDTO)
            .toList();
    }

    public List<ExcusaResponseDTO> buscar(int legajo, LocalDate fechaDesde, LocalDate fechaHasta) {
        LocalDateTime desde = fechaDesde.atStartOfDay();
        LocalDateTime hasta = fechaHasta.atTime(23, 59, 59);

        return repository.findByEmpleadoNroLegajoAndFechaCreacionBetween(legajo, desde, hasta)
            .stream()
            .map(mapper::toDTO)
            .toList();
    }

    @Transactional
    public long eliminarAnterioresA(LocalDate fechaLimite) {
        if (fechaLimite == null) {
            throw new IllegalArgumentException(
                "El parámetro fechaLimite es obligatorio para eliminar excusas");
        }

        LocalDateTime limite = fechaLimite.atTime(23, 59, 59);
        long cantidad = repository.countByFechaCreacionBefore(limite);
        repository.deleteByFechaCreacionBefore(limite);
        return cantidad;
    }

    private TipoMotivo parsearMotivo(String motivo) {
        try {
            return TipoMotivo.valueOf(motivo.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Motivo no válido: '" + motivo + "'. Valores permitidos: " +
                    java.util.Arrays.toString(TipoMotivo.values()));
        }
    }
}
