package ar.edu.utn.excusassa.estado;

import ar.edu.utn.excusassa.chain.Responsable;
import ar.edu.utn.excusassa.modelo.Excusa;

public interface EstadoAnimo {

    void manejarExcusa(Responsable responsable, Excusa excusa);
}
