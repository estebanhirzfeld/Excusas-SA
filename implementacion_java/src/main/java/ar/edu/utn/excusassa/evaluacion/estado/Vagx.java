package ar.edu.utn.excusassa.evaluacion.estado;

import ar.edu.utn.excusassa.evaluacion.Responsable;
import ar.edu.utn.excusassa.modelo.Excusa;

public class Vagx implements EstadoAnimo {

    private static final String CTO_EMAIL = "cto@excusassa.com";

    @Override
    public void manejarExcusa(Responsable responsable, Excusa excusa) {
        responsable.notificarPorEmail(
            CTO_EMAIL,
            "Excusa derivada por estado Vagx",
            "El responsable " + responsable.getNombre() + " derivó la excusa del empleado "
                + excusa.getEmpleado().getNombre() + " por estar en estado Vagx."
        );

        responsable.derivarAlSiguiente(excusa);
    }
}
