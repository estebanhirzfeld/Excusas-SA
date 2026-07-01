package ar.edu.utn.excusassa.modelo.motivo;

public class Inverosimil extends Motivo {

    @Override
    public String getNombre() {
        return "Inverosímil";
    }

    @Override
    public String getDescripcion() {
        return "El empleado presentó una excusa inverosímil";
    }
}
