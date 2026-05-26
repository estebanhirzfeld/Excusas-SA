package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.*;
import ar.edu.utn.excusassa.notificacion.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("GerenteRRHH (ConcreteHandler)")
class GerenteRRHHTest {

    @Mock
    private EmailSender emailSender;

    private GerenteRRHH gerente;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        gerente = new GerenteRRHH("Carlos", "carlos@excusassa.com", 30, emailSender);
        empleado = new Empleado("Pedro", "pedro@mail.com", 300);
    }

    @Test
    @DisplayName("acepta excusas con motivo INVEROSIMIL")
    void aceptaInverosimil() {
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());

        gerente.evaluarEnCadena(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("NO acepta excusas con motivo DORMIDO")
    void noAceptaDormido() {
        Excusa excusa = new Excusa(empleado, new MotivoDormido());

        gerente.evaluarEnCadena(excusa);

        assertFalse(excusa.isAceptada());
    }

    @Test
    @DisplayName("NO acepta excusas con motivo SUMINISTRO")
    void noAceptaSuministro() {
        Excusa excusa = new Excusa(empleado, new MotivoSuministro());

        gerente.evaluarEnCadena(excusa);

        assertFalse(excusa.isAceptada());
    }
}
