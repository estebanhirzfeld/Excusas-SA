package ar.edu.davinci.excusassa.evaluacion;

import ar.edu.davinci.excusassa.evaluacion.estado.Productivx;
import ar.edu.davinci.excusassa.modelo.Empleado;
import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.Dormido;
import ar.edu.davinci.excusassa.notificacion.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RechazadorDeExcusas (Null Object)")
class RechazadorDeExcusasTest {

    @Mock
    private EmailSender emailSender;

    private RechazadorDeExcusas rechazador;
    private Excusa excusa;

    @BeforeEach
    void setUp() {
        rechazador = new RechazadorDeExcusas(emailSender);
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        excusa = new Excusa(empleado, new Dormido());
    }

    @Test
    @DisplayName("siempre rechaza la excusa")
    void siempreRechaza() {
        rechazador.revisarExcusa(excusa);

        assertFalse(excusa.isAceptada());
        assertEquals("Excusa rechazada: necesitamos pruebas contundentes", excusa.getMensajeResultado());
    }

    @Test
    @DisplayName("no deriva la excusa aunque se le setee un siguiente")
    void noDerivaNunca() {
        Recepcionista siguiente = new Recepcionista("R", "r@mail.com", 1, emailSender);
        rechazador.setSiguiente(siguiente);

        rechazador.revisarExcusa(excusa);

        assertFalse(excusa.isAceptada());
        assertEquals(0, siguiente.getExcusasProcesadas());
    }

    @Test
    @DisplayName("setEstadoAnimo no tiene efecto")
    void estadoAnimoNoHaceNada() {
        rechazador.setEstadoAnimo(new Productivx());

        assertNull(rechazador.getEstadoAnimo());
    }
}
