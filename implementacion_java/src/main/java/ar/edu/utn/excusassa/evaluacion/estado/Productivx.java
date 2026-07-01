package ar.edu.utn.excusassa.evaluacion.estado;

import ar.edu.utn.excusassa.evaluacion.Responsable;
import ar.edu.utn.excusassa.modelo.Excusa;

public class Productivx implements EstadoAnimo {

    @Override
    public void manejarExcusa(Responsable responsable, Excusa excusa) {
        if (responsable.puedeProcesarExcusa(excusa)) {
            responsable.evaluarEnCadena(excusa);
        } else if (responsable.tieneSiguiente()) {
            responsable.derivarAlSiguiente(excusa);
        } else {
            responsable.forzarProcesar(excusa);
        }
    }
}
