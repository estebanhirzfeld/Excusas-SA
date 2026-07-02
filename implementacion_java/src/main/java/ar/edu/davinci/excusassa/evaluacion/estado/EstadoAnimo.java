package ar.edu.davinci.excusassa.evaluacion.estado;

import ar.edu.davinci.excusassa.evaluacion.Responsable;
import ar.edu.davinci.excusassa.modelo.Excusa;

public interface EstadoAnimo {

    void manejarExcusa(Responsable responsable, Excusa excusa);
}
