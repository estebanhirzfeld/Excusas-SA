package ar.edu.utn.excusassa.dto;

import java.time.LocalDateTime;

public class ExcusaResponseDTO {

    private Long id;
    private String nombreEmpleado;
    private int legajoEmpleado;
    private String motivo;
    private boolean aceptada;
    private String mensajeResultado;
    private String encargadoQueProceso;
    private LocalDateTime fechaCreacion;

    public ExcusaResponseDTO(Long id, String nombreEmpleado, int legajoEmpleado,
                              String motivo, boolean aceptada, String mensajeResultado,
                              String encargadoQueProceso, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombreEmpleado = nombreEmpleado;
        this.legajoEmpleado = legajoEmpleado;
        this.motivo = motivo;
        this.aceptada = aceptada;
        this.mensajeResultado = mensajeResultado;
        this.encargadoQueProceso = encargadoQueProceso;
        this.fechaCreacion = fechaCreacion;
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

    public boolean isAceptada() {
        return aceptada;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public String getEncargadoQueProceso() {
        return encargadoQueProceso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
