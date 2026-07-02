package ar.edu.davinci.excusassa.prontuario;

import ar.edu.davinci.excusassa.modelo.Empleado;
import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.Inverosimil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Prontuario")
class ProntuarioTest {

    @Test
    @DisplayName("se crea con empleado y excusa")
    void seCreaCorrecto() {
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        Excusa excusa = new Excusa(empleado, new Inverosimil());

        Prontuario prontuario = new Prontuario(empleado, excusa);

        assertSame(empleado, prontuario.getEmpleado());
        assertSame(excusa, prontuario.getExcusa());
    }

    @Test
    @DisplayName("el nro de legajo viene del empleado")
    void nroLegajoDelEmpleado() {
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        Excusa excusa = new Excusa(empleado, new Inverosimil());
        Prontuario prontuario = new Prontuario(empleado, excusa);

        assertEquals(100, prontuario.getEmpleado().getNroLegajo());
    }
}
