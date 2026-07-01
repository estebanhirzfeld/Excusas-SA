package ar.edu.utn.excusassa.modelo.motivo;

public class Transporte extends Motivo {

    @Override
    public String getNombre() {
        return "Transporte";
    }

    @Override
    public String getDescripcion() {
        return "El empleado perdió un transporte público";
    }
}
