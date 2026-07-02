package ar.edu.davinci.excusassa.evaluacion.estado;

import ar.edu.davinci.excusassa.evaluacion.Responsable;
import ar.edu.davinci.excusassa.modelo.Excusa;

public class Normal implements EstadoAnimo {

    @Override
    public void manejarExcusa(Responsable responsable, Excusa excusa) {
        responsable.evaluarEnCadena(excusa);
    }
}
