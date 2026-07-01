package ar.edu.utn.excusassa.modelo.motivo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Motivos (polimorfismo y value object)")
class MotivoTest {

    @Test
    @DisplayName("Dormido retorna nombre y descripción correctos")
    void motivoDormido() {
        Motivo motivo = new Dormido();

        assertEquals("Dormido", motivo.getNombre());
        assertNotNull(motivo.getDescripcion());
        assertFalse(motivo.getDescripcion().isEmpty());
    }

    @Test
    @DisplayName("Transporte retorna nombre y descripción correctos")
    void motivoTransporte() {
        Motivo motivo = new Transporte();

        assertEquals("Transporte", motivo.getNombre());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("Suministro retorna nombre y descripción correctos")
    void motivoSuministro() {
        Motivo motivo = new Suministro();

        assertEquals("Suministro", motivo.getNombre());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("FamiliarACargo retorna nombre y descripción correctos")
    void motivoFamiliar() {
        Motivo motivo = new FamiliarACargo();

        assertEquals("Familiar a cargo", motivo.getNombre());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("Inverosimil retorna nombre y descripción correctos")
    void motivoInverosimil() {
        Motivo motivo = new Inverosimil();

        assertEquals("Inverosímil", motivo.getNombre());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("todos los motivos son instancias de Motivo (polimorfismo)")
    void todosImplementanMotivo() {
        Motivo[] motivos = {
            new Dormido(),
            new Transporte(),
            new Suministro(),
            new FamiliarACargo(),
            new Inverosimil()
        };

        for (Motivo m : motivos) {
            assertNotNull(m.getNombre());
            assertNotNull(m.getDescripcion());
        }
    }

    @Test
    @DisplayName("igualdad por valor (equals funciona correctamente)")
    void igualdadPorValor() {
        Motivo m1 = new Dormido();
        Motivo m2 = new Dormido();
        Motivo m3 = new Transporte();

        assertEquals(m1, m2);
        assertNotEquals(m1, m3);
        assertEquals(m1.hashCode(), m2.hashCode());
    }
}
