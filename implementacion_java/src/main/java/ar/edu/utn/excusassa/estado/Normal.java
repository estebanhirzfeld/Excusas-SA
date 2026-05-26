package ar.edu.utn.excusassa.estado;

import ar.edu.utn.excusassa.chain.Responsable;
import ar.edu.utn.excusassa.modelo.Excusa;

public class Normal implements EstadoAnimo {

    @Override
    public void manejarExcusa(Responsable responsable, Excusa excusa) {
        responsable.evaluarEnCadena(excusa);
    }
}
