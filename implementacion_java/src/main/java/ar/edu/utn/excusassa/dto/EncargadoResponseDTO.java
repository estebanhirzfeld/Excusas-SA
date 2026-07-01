package ar.edu.utn.excusassa.dto;

import java.util.List;

public class EncargadoResponseDTO {

    private String nombre;
    private String email;
    private int nroLegajo;
    private String modo;
    private List<String> motivosQueManeja;
    private int excusasProcesadas;

    public EncargadoResponseDTO(String nombre, String email, int nroLegajo,
                                 String modo, List<String> motivosQueManeja,
                                 int excusasProcesadas) {
        this.nombre = nombre;
        this.email = email;
        this.nroLegajo = nroLegajo;
        this.modo = modo;
        this.motivosQueManeja = motivosQueManeja;
        this.excusasProcesadas = excusasProcesadas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getNroLegajo() {
        return nroLegajo;
    }

    public String getModo() {
        return modo;
    }

    public List<String> getMotivosQueManeja() {
        return motivosQueManeja;
    }

    public int getExcusasProcesadas() {
        return excusasProcesadas;
    }
}
