package ar.edu.utn.excusassa.prontuario;

import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.MotivoInverosimil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Prontuario")
class ProntuarioTest {

    @Test
    @DisplayName("se crea con empleado y excusa")
    void seCreaCorrecto() {
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());

        Prontuario prontuario = new Prontuario(empleado, excusa);

        assertSame(empleado, prontuario.getEmpleado());
        assertSame(excusa, prontuario.getExcusa());
    }

    @Test
    @DisplayName("el nro de legajo viene del empleado")
    void nroLegajoDelEmpleado() {
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());
        Prontuario prontuario = new Prontuario(empleado, excusa);

        assertEquals(100, prontuario.getEmpleado().getNroLegajo());
    }
}
