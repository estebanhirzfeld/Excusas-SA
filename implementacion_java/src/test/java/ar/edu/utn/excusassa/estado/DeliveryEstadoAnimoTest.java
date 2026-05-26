package ar.edu.utn.excusassa.estado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DeliveryEstadoAnimo (Registry OCP)")
class DeliveryEstadoAnimoTest {

    private DeliveryEstadoAnimo delivery;

    @BeforeEach
    void setUp() {
        delivery = new DeliveryEstadoAnimo();
    }

    @Test
    @DisplayName("registra y obtiene un estado por nombre")
    void registraYObtiene() {
        EstadoAnimo normal = new Normal();
        delivery.registrar("normal", normal);

        assertSame(normal, delivery.obtener("normal"));
    }

    @Test
    @DisplayName("retorna null si el estado no está registrado")
    void retornaNullSiNoExiste() {
        assertNull(delivery.obtener("inexistente"));
    }

    @Test
    @DisplayName("permite registrar estados dinámicamente (OCP)")
    void registraDinamicamente() {
        delivery.registrar("normal", new Normal());
        delivery.registrar("productivx", new Productivx());
        delivery.registrar("vagx", new Vagx());

        assertNotNull(delivery.obtener("normal"));
        assertNotNull(delivery.obtener("productivx"));
        assertNotNull(delivery.obtener("vagx"));
    }

    @Test
    @DisplayName("puede agregar estados nuevos sin modificar código existente")
    void agregarEstadoNuevoEnRuntime() {
        // Simulamos un estado nuevo creado en runtime
        EstadoAnimo estadoCustom = (responsable, excusa) -> {
            // comportamiento custom
            responsable.evaluarEnCadena(excusa);
        };

        delivery.registrar("super_productivx", estadoCustom);

        assertSame(estadoCustom, delivery.obtener("super_productivx"));
    }
}
