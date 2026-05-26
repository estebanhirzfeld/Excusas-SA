package ar.edu.utn.excusassa.chain;

import java.util.ArrayList;
import java.util.List;

public class ChainOfBuilder {

    private final List<Responsable> responsables;

    public ChainOfBuilder() {
        this.responsables = new ArrayList<>();
    }

    public ChainOfBuilder agregar(Responsable responsable) {
        responsables.add(responsable);
        return this;
    }

    public Responsable construir() {
        
        RechazadorDeExcusas rechazador = new RechazadorDeExcusas(null);
        responsables.add(rechazador);

        for (int i = 0; i < responsables.size() - 1; i++) {
            responsables.get(i).setSiguiente(responsables.get(i + 1));
        }

        return responsables.get(0);
    }
}
