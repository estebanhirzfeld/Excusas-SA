package ar.edu.davinci.excusassa.service;

import ar.edu.davinci.excusassa.dto.EmpleadoRequestDTO;
import ar.edu.davinci.excusassa.dto.EmpleadoResponseDTO;
import ar.edu.davinci.excusassa.mapper.EmpleadoMapper;
import ar.edu.davinci.excusassa.modelo.Empleado;
import ar.edu.davinci.excusassa.repository.EmpleadoRepository;
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
