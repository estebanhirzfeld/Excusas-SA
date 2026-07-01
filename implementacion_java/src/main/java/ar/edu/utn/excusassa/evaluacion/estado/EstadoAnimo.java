package ar.edu.utn.excusassa.evaluacion.estado;

import ar.edu.utn.excusassa.evaluacion.Responsable;
import ar.edu.utn.excusassa.modelo.Excusa;

public interface EstadoAnimo {

    void manejarExcusa(Responsable responsable, Excusa excusa);
}
