package ar.edu.davinci.excusassa.dto;

public class EmpleadoResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private int nroLegajo;

    public EmpleadoResponseDTO(Long id, String nombre, String email, int nroLegajo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.nroLegajo = nroLegajo;
    }

    public Long getId() {
        return id;
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
}
