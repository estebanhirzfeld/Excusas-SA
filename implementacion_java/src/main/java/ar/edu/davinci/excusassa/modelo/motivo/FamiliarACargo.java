package ar.edu.davinci.excusassa.modelo.motivo;

public class FamiliarACargo extends Motivo {

    @Override
    public String getNombre() {
        return "Familiar a cargo";
    }

    @Override
    public String getDescripcion() {
        return "El empleado tuvo que cuidar a un familiar a cargo";
    }
}
