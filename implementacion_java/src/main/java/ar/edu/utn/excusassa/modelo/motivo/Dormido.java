package ar.edu.utn.excusassa.modelo.motivo;

public class Dormido extends Motivo {

    @Override
    public String getNombre() {
        return "Dormido";
    }

    @Override
    public String getDescripcion() {
        return "El empleado se quedó dormido";
    }
}
