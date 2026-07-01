package ar.edu.utn.excusassa.modelo.motivo;

import java.util.function.Supplier;

public enum TipoMotivo {

    DORMIDO(Dormido::new),
    TRANSPORTE(Transporte::new),
    SUMINISTRO(Suministro::new),
    FAMILIAR_A_CARGO(FamiliarACargo::new),
    INVEROSIMIL(Inverosimil::new);

    private final Supplier<Motivo> factory;

    TipoMotivo(Supplier<Motivo> factory) {
        this.factory = factory;
    }

    public Motivo crearMotivo() {
        return factory.get();
    }
}
