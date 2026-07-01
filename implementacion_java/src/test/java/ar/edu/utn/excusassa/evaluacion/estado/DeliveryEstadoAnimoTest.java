package ar.edu.utn.excusassa.evaluacion.estado;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Delivery de Estado Animo")
class DeliveryEstadoAnimoTest {

    @Test
    @DisplayName("se puede registrar y obtener un estado por nombre")
    void registraYObtiene() {
        DeliveryEstadoAnimo delivery = new DeliveryEstadoAnimo();
        EstadoAnimo normal = new Normal();

        delivery.registrar("normal", normal);

        EstadoAnimo obtenido = delivery.obtener("normal");
        assertSame(normal, obtenido);
    }

    @Test
    @DisplayName("devuelve null si el estado no existe")
    void obtenerNull() {
        DeliveryEstadoAnimo delivery = new DeliveryEstadoAnimo();

        assertNull(delivery.obtener("inexistente"));
    }

    @Test
    @DisplayName("puede registrar múltiples estados")
    void registrarMultiples() {
        DeliveryEstadoAnimo delivery = new DeliveryEstadoAnimo();
        EstadoAnimo normal = new Normal();
        EstadoAnimo productivx = new Productivx();
        EstadoAnimo vagx = new Vagx();

        delivery.registrar("normal", normal);
        delivery.registrar("productivx", productivx);
        delivery.registrar("vagx", vagx);

        assertSame(normal, delivery.obtener("normal"));
        assertSame(productivx, delivery.obtener("productivx"));
        assertSame(vagx, delivery.obtener("vagx"));
    }
}
