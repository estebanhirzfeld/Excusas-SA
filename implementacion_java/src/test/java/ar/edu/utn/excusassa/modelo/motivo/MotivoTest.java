package ar.edu.utn.excusassa.modelo.motivo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Motivos (polimorfismo)")
class MotivoTest {

    @Test
    @DisplayName("MotivoDormido retorna tipo y descripción correctos")
    void motivoDormido() {
        Motivo motivo = new MotivoDormido();

        assertEquals("DORMIDO", motivo.getTipo());
        assertNotNull(motivo.getDescripcion());
        assertFalse(motivo.getDescripcion().isEmpty());
    }

    @Test
    @DisplayName("MotivoTransporte retorna tipo y descripción correctos")
    void motivoTransporte() {
        Motivo motivo = new MotivoTransporte();

        assertEquals("TRANSPORTE", motivo.getTipo());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("MotivoSuministro retorna tipo y descripción correctos")
    void motivoSuministro() {
        Motivo motivo = new MotivoSuministro();

        assertEquals("SUMINISTRO", motivo.getTipo());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("MotivoFamiliarACargo retorna tipo y descripción correctos")
    void motivoFamiliar() {
        Motivo motivo = new MotivoFamiliarACargo();

        assertEquals("FAMILIAR", motivo.getTipo());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("MotivoInverosimil retorna tipo y descripción correctos")
    void motivoInverosimil() {
        Motivo motivo = new MotivoInverosimil();

        assertEquals("INVEROSIMIL", motivo.getTipo());
        assertNotNull(motivo.getDescripcion());
    }

    @Test
    @DisplayName("todos los motivos son instancias de Motivo (polimorfismo)")
    void todosImplementanMotivo() {
        Motivo[] motivos = {
            new MotivoDormido(),
            new MotivoTransporte(),
            new MotivoSuministro(),
            new MotivoFamiliarACargo(),
            new MotivoInverosimil()
        };

        for (Motivo m : motivos) {
            assertNotNull(m.getTipo());
            assertNotNull(m.getDescripcion());
        }
    }
}
