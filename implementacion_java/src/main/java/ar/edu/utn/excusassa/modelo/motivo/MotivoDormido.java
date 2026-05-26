package ar.edu.utn.excusassa.modelo.motivo;

public class MotivoDormido implements Motivo {

    @Override
    public String getTipo() {
        return "DORMIDO";
    }

    @Override
    public String getDescripcion() {
        return "El empleado se quedó dormido";
    }
}
