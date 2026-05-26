package ar.edu.utn.excusassa.modelo.motivo;

public class MotivoSuministro implements Motivo {

    @Override
    public String getTipo() {
        return "SUMINISTRO";
    }

    @Override
    public String getDescripcion() {
        return "El empleado sufrió pérdida de suministro eléctrico";
    }
}
