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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("SupervisoraDeArea (ConcreteHandler)")
class SupervisoraDeAreaTest {

    @Mock
    private EmailSender emailSender;

    private SupervisoraDeArea supervisora;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        supervisora = new SupervisoraDeArea("Laura", "laura@excusassa.com", 20, emailSender);
        empleado = new Empleado("Ana", "ana@mail.com", 200);
    }

    @Test
    @DisplayName("acepta excusas con motivo SUMINISTRO")
    void aceptaSuministro() {
        Excusa excusa = new Excusa(empleado, new MotivoSuministro());

        supervisora.evaluarEnCadena(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("acepta excusas con motivo FAMILIAR")
    void aceptaFamiliar() {
        Excusa excusa = new Excusa(empleado, new MotivoFamiliarACargo());

        supervisora.evaluarEnCadena(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("NO acepta excusas con motivo DORMIDO")
    void noAceptaDormido() {
        Excusa excusa = new Excusa(empleado, new MotivoDormido());

        supervisora.evaluarEnCadena(excusa);

        assertFalse(excusa.isAceptada());
    }

    @Test
    @DisplayName("SUMINISTRO: envía email a EDESUR consultando")
    void suministroEnviaEmailAEdesur() {
        Excusa excusa = new Excusa(empleado, new MotivoSuministro());

        supervisora.evaluarEnCadena(excusa);

        verify(emailSender).enviarEmail(
            eq("EDESUR@mailfake.com.ar"),
            eq("laura@excusassa.com"),
            anyString(),
            anyString()
        );
    }

    @Test
    @DisplayName("FAMILIAR: envía email al empleado preguntando si está bien")
    void familiarEnviaEmailAlEmpleado() {
        Excusa excusa = new Excusa(empleado, new MotivoFamiliarACargo());

        supervisora.evaluarEnCadena(excusa);

        verify(emailSender).enviarEmail(
            eq("ana@mail.com"),
            eq("laura@excusassa.com"),
            anyString(),
            contains("todo está bien")
        );
    }
}
