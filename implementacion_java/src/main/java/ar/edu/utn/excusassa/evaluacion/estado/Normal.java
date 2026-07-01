package ar.edu.utn.excusassa.evaluacion.estado;

import ar.edu.utn.excusassa.evaluacion.Responsable;
import ar.edu.utn.excusassa.modelo.Excusa;

public class Normal implements EstadoAnimo {

    @Override
    public void manejarExcusa(Responsable responsable, Excusa excusa) {
        responsable.evaluarEnCadena(excusa);
    }
}
