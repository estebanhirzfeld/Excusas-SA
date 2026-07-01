package ar.edu.utn.excusassa.dto;

import java.time.LocalDateTime;

public class ProntuarioResponseDTO {

    private Long id;
    private String nombreEmpleado;
    private int legajoEmpleado;
    private String motivo;
    private String mensajeExcusa;
    private LocalDateTime fechaExcusa;

    public ProntuarioResponseDTO(Long id, String nombreEmpleado, int legajoEmpleado,
                                  String motivo, String mensajeExcusa, LocalDateTime fechaExcusa) {
        this.id = id;
        this.nombreEmpleado = nombreEmpleado;
        this.legajoEmpleado = legajoEmpleado;
        this.motivo = motivo;
        this.mensajeExcusa = mensajeExcusa;
        this.fechaExcusa = fechaExcusa;
    }

    public Long getId() {
        return id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public int getLegajoEmpleado() {
        return legajoEmpleado;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getMensajeExcusa() {
        return mensajeExcusa;
    }

    public LocalDateTime getFechaExcusa() {
        return fechaExcusa;
    }
}
