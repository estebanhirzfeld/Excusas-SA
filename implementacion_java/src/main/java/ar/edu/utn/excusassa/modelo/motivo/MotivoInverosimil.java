package ar.edu.utn.excusassa.modelo.motivo;

public class MotivoInverosimil implements Motivo {

    @Override
    public String getTipo() {
        return "INVEROSIMIL";
    }

    @Override
    public String getDescripcion() {
        return "El empleado presentó una excusa inverosímil";
    }
}
