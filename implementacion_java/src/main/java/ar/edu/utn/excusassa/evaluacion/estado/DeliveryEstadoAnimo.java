package ar.edu.utn.excusassa.evaluacion.estado;

import java.util.HashMap;
import java.util.Map;

public class DeliveryEstadoAnimo {

    private final Map<String, EstadoAnimo> estadosDisponibles;

    public DeliveryEstadoAnimo() {
        this.estadosDisponibles = new HashMap<>();
    }

    public void registrar(String nombre, EstadoAnimo estado) {
        estadosDisponibles.put(nombre, estado);
    }

    public EstadoAnimo obtener(String nombre) {
        return estadosDisponibles.get(nombre);
    }
}
