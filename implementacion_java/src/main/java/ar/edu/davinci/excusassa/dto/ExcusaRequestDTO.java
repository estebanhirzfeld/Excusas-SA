package ar.edu.davinci.excusassa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ExcusaRequestDTO {

    @Positive(message = "El legajo del empleado debe ser positivo")
    private int legajoEmpleado;

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    public int getLegajoEmpleado() {
        return legajoEmpleado;
    }

    public void setLegajoEmpleado(int legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
