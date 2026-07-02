package ar.edu.davinci.excusassa.mapper;

import ar.edu.davinci.excusassa.dto.ExcusaResponseDTO;
import ar.edu.davinci.excusassa.modelo.Excusa;
import org.springframework.stereotype.Component;

@Component
public class ExcusaMapper {

    public ExcusaResponseDTO toDTO(Excusa excusa) {
        return new ExcusaResponseDTO(
            excusa.getId(),
            excusa.getEmpleado().getNombre(),
            excusa.getEmpleado().getNroLegajo(),
            excusa.getTipoMotivo() != null ? excusa.getTipoMotivo().name() : "DESCONOCIDO",
            excusa.isAceptada(),
            excusa.getMensajeResultado(),
            excusa.getEncargadoQueProceso(),
            excusa.getFechaCreacion()
        );
    }
}
