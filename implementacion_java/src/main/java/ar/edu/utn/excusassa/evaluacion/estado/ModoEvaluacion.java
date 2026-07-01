package ar.edu.utn.excusassa.evaluacion.estado;

import java.util.function.Supplier;

public enum ModoEvaluacion {

    NORMAL(Normal::new),
    VAGO(Vagx::new),
    PRODUCTIVO(Productivx::new);

    private final Supplier<EstadoAnimo> factory;

    ModoEvaluacion(Supplier<EstadoAnimo> factory) {
        this.factory = factory;
    }

    public EstadoAnimo crearEstado() {
        return factory.get();
    }
}
