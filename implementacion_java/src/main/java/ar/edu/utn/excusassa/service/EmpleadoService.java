package ar.edu.utn.excusassa.service;

import ar.edu.utn.excusassa.dto.EmpleadoRequestDTO;
import ar.edu.utn.excusassa.dto.EmpleadoResponseDTO;
import ar.edu.utn.excusassa.mapper.EmpleadoMapper;
import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;
    private final EmpleadoMapper mapper;

    public EmpleadoService(EmpleadoRepository repository, EmpleadoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<EmpleadoResponseDTO> listarTodos() {
        return repository.findAll().stream()
            .map(mapper::toDTO)
            .toList();
    }

    public EmpleadoResponseDTO crear(EmpleadoRequestDTO dto) {
        if (repository.existsByNroLegajo(dto.getNroLegajo())) {
            throw new IllegalArgumentException(
                "Ya existe un empleado con legajo " + dto.getNroLegajo());
        }

        Empleado empleado = mapper.toEntity(dto);
        Empleado guardado = repository.save(empleado);
        return mapper.toDTO(guardado);
    }

    public Empleado buscarPorLegajo(int legajo) {
        return repository.findByNroLegajo(legajo)
            .orElseThrow(() -> new NoSuchElementException(
                "No se encontró empleado con legajo " + legajo));
    }
}
