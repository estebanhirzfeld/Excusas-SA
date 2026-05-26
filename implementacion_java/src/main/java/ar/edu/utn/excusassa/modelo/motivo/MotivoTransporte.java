package ar.edu.utn.excusassa.modelo.motivo;

public class MotivoTransporte implements Motivo {

    @Override
    public String getTipo() {
        return "TRANSPORTE";
    }

    @Override
    public String getDescripcion() {
        return "El empleado perdió un transporte público";
    }
}
