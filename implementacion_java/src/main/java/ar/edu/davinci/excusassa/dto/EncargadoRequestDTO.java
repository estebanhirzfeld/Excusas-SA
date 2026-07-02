package ar.edu.davinci.excusassa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class EncargadoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Positive(message = "El nro de legajo debe ser positivo")
    private int nroLegajo;

    @NotEmpty(message = "Debe indicar al menos un motivo que puede manejar")
    private List<String> motivosQuePuedeManejear;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNroLegajo() {
        return nroLegajo;
    }

    public void setNroLegajo(int nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public List<String> getMotivosQuePuedeManejear() {
        return motivosQuePuedeManejear;
    }

    public void setMotivosQuePuedeManejear(List<String> motivosQuePuedeManejear) {
        this.motivosQuePuedeManejear = motivosQuePuedeManejear;
    }
}
