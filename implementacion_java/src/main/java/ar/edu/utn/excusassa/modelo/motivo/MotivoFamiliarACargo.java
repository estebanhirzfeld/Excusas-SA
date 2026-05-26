package ar.edu.utn.excusassa.modelo.motivo;

public class MotivoFamiliarACargo implements Motivo {

    @Override
    public String getTipo() {
        return "FAMILIAR";
    }

    @Override
    public String getDescripcion() {
        return "El empleado tuvo que cuidar a un familiar a cargo";
    }
}
