package ar.edu.utn.excusassa.mapper;

import ar.edu.utn.excusassa.dto.EmpleadoRequestDTO;
import ar.edu.utn.excusassa.dto.EmpleadoResponseDTO;
import ar.edu.utn.excusassa.modelo.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public Empleado toEntity(EmpleadoRequestDTO dto) {
        return new Empleado(dto.getNombre(), dto.getEmail(), dto.getNroLegajo());
    }

    public EmpleadoResponseDTO toDTO(Empleado empleado) {
        return new EmpleadoResponseDTO(
            empleado.getId(),
            empleado.getNombre(),
            empleado.getEmail(),
            empleado.getNroLegajo()
        );
    }
}
