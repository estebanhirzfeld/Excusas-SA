package ar.edu.utn.excusassa.modelo.motivo;

public class Suministro extends Motivo {

    @Override
    public String getNombre() {
        return "Suministro";
    }

    @Override
    public String getDescripcion() {
        return "El empleado sufrió pérdida de suministro eléctrico";
    }
}
