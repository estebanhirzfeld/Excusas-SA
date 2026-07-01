package ar.edu.utn.excusassa.mapper;

import ar.edu.utn.excusassa.dto.ProntuarioResponseDTO;
import ar.edu.utn.excusassa.prontuario.Prontuario;
import org.springframework.stereotype.Component;

@Component
public class ProntuarioMapper {

    public ProntuarioResponseDTO toDTO(Prontuario prontuario) {
        return new ProntuarioResponseDTO(
            prontuario.getId(),
            prontuario.getEmpleado().getNombre(),
            prontuario.getEmpleado().getNroLegajo(),
            prontuario.getExcusa().getTipoMotivo() != null
                ? prontuario.getExcusa().getTipoMotivo().name()
                : "DESCONOCIDO",
            prontuario.getExcusa().getMensajeResultado(),
            prontuario.getExcusa().getFechaCreacion()
        );
    }
}
