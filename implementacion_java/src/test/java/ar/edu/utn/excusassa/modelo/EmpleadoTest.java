package ar.edu.utn.excusassa.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Empleado")
class EmpleadoTest {

    @Test
    @DisplayName("se crea con nombre, email y nro de legajo")
    void seCreaCon3Atributos() {
        Empleado empleado = new Empleado("Juan Pérez", "juan@mail.com", 1234);

        assertEquals("Juan Pérez", empleado.getNombre());
        assertEquals("juan@mail.com", empleado.getEmail());
        assertEquals(1234, empleado.getNroLegajo());
    }

    @Test
    @DisplayName("dos empleados con distinto legajo no son iguales")
    void dosEmpleadosDistintos() {
        Empleado e1 = new Empleado("Juan", "juan@mail.com", 1);
        Empleado e2 = new Empleado("Juan", "juan@mail.com", 2);

        assertNotEquals(e1.getNroLegajo(), e2.getNroLegajo());
    }
}
