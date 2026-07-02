package ar.edu.davinci.excusassa.evaluacion.estado;

import ar.edu.davinci.excusassa.evaluacion.Responsable;
import ar.edu.davinci.excusassa.modelo.Excusa;

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
