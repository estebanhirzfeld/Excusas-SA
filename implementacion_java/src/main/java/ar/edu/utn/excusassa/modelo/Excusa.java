package ar.edu.utn.excusassa.modelo;

import ar.edu.utn.excusassa.modelo.motivo.Motivo;

public class Excusa {

    private final Empleado empleado;
    private final Motivo motivo;
    private boolean aceptada;
    private String mensajeResultado;

    public Excusa(Empleado empleado, Motivo motivo) {
        this.empleado = empleado;
        this.motivo = motivo;
        this.aceptada = false;
        this.mensajeResultado = null;
    }

    public void aceptar() {
        this.aceptada = true;
        this.mensajeResultado = "Excusa aceptada";
    }

    public void aceptar(String mensaje) {
        this.aceptada = true;
        this.mensajeResultado = mensaje;
    }

    public void rechazar(String motivo) {
        this.aceptada = false;
        this.mensajeResultado = motivo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }
}
