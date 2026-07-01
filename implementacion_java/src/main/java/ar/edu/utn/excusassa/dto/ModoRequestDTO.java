package ar.edu.utn.excusassa.dto;

import jakarta.validation.constraints.NotBlank;

public class ModoRequestDTO {

    @NotBlank(message = "El modo es obligatorio (NORMAL, VAGO, PRODUCTIVO)")
    private String modo;

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
}
