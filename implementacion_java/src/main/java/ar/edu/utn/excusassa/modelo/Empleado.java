package ar.edu.utn.excusassa.modelo;

public class Empleado {

    private final String nombre;
    private final String email;
    private final int nroLegajo;

    public Empleado(String nombre, String email, int nroLegajo) {
        this.nombre = nombre;
        this.email = email;
        this.nroLegajo = nroLegajo;
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
