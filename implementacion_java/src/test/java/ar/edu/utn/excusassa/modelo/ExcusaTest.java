package ar.edu.utn.excusassa.modelo;

import ar.edu.utn.excusassa.modelo.motivo.Motivo;
import ar.edu.utn.excusassa.modelo.motivo.MotivoDormido;
import ar.edu.utn.excusassa.modelo.motivo.MotivoInverosimil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Excusa")
class ExcusaTest {

    private final Empleado empleado = new Empleado("Ana", "ana@mail.com", 100);

    @Test
    @DisplayName("se crea con empleado y motivo, no aceptada por defecto")
    void seCreaSinAceptar() {
        Motivo motivo = new MotivoDormido();
        Excusa excusa = new Excusa(empleado, motivo);

        assertSame(empleado, excusa.getEmpleado());
        assertSame(motivo, excusa.getMotivo());
        assertFalse(excusa.isAceptada());
        assertNull(excusa.getMensajeResultado());
    }

    @Test
    @DisplayName("se puede aceptar una excusa")
    void aceptarExcusa() {
        Excusa excusa = new Excusa(empleado, new MotivoDormido());

        excusa.aceptar();

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("se puede rechazar una excusa con mensaje")
    void rechazarExcusa() {
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());

        excusa.rechazar("Excusa rechazada: necesitamos pruebas contundentes");

        assertFalse(excusa.isAceptada());
        assertEquals("Excusa rechazada: necesitamos pruebas contundentes", excusa.getMensajeResultado());
    }

    @Test
    @DisplayName("aceptar setea mensaje de resultado")
    void aceptarSeteaMensaje() {
        Excusa excusa = new Excusa(empleado, new MotivoDormido());

        excusa.aceptar();

        assertNotNull(excusa.getMensajeResultado());
    }
}
