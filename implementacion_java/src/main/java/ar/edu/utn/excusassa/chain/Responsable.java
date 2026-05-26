package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.estado.EstadoAnimo;
import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.notificacion.EmailSender;

public abstract class Responsable extends Empleado {

    private Responsable siguiente;
    private EstadoAnimo estadoAnimo;
    private int excusasProcesadas;
    protected EmailSender emailSender;

    public Responsable(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        super(nombre, email, nroLegajo);
        this.emailSender = emailSender;
        this.excusasProcesadas = 0;
    }

    public void revisarExcusa(Excusa excusa) {
        if (estadoAnimo != null) {
            estadoAnimo.manejarExcusa(this, excusa);
        } else {
            evaluarEnCadena(excusa);
        }
    }

    public void evaluarEnCadena(Excusa excusa) {
        if (puedeProcesar(excusa)) {
            procesar(excusa);
            incrementarExcusasProcesadas();
        } else {
            derivarAlSiguiente(excusa);
        }
    }

    protected abstract boolean puedeProcesar(Excusa excusa);

    protected abstract void procesar(Excusa excusa);

    public void derivarAlSiguiente(Excusa excusa) {
        if (siguiente != null) {
            siguiente.revisarExcusa(excusa);
        }
    }

    public boolean tieneSiguiente() {
        return siguiente != null;
    }

    private void incrementarExcusasProcesadas() {
        this.excusasProcesadas++;
    }

    public void setSiguiente(Responsable siguiente) {
        this.siguiente = siguiente;
    }

    public Responsable getSiguiente() {
        return siguiente;
    }

    public void setEstadoAnimo(EstadoAnimo estadoAnimo) {
        this.estadoAnimo = estadoAnimo;
    }

    public EstadoAnimo getEstadoAnimo() {
        return estadoAnimo;
    }

    public int getExcusasProcesadas() {
        return excusasProcesadas;
    }

    public boolean puedeProcesarExcusa(Excusa excusa) {
        return puedeProcesar(excusa);
    }

    public void forzarProcesar(Excusa excusa) {
        procesar(excusa);
        incrementarExcusasProcesadas();
    }

    public void notificarPorEmail(String destino, String asunto, String cuerpo) {
        if (emailSender != null) {
            emailSender.enviarEmail(destino, this.getEmail(), asunto, cuerpo);
        }
    }
}
