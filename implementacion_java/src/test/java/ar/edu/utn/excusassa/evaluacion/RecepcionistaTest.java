package ar.edu.utn.excusassa.evaluacion;

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
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
@DisplayName("Recepcionista (ConcreteHandler)")
class RecepcionistaTest {

    @Mock
    private EmailSender emailSender;

    private Recepcionista recepcionista;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        recepcionista = new Recepcionista("María", "maria@excusassa.com", 10, emailSender);
        empleado = new Empleado("Juan", "juan@mail.com", 100);
    }

    @Test
    @DisplayName("acepta excusas con motivo DORMIDO")
    void aceptaDormido() {
        Excusa excusa = new Excusa(empleado, new Dormido());

        recepcionista.evaluarEnCadena(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("acepta excusas con motivo TRANSPORTE")
    void aceptaTransporte() {
        Excusa excusa = new Excusa(empleado, new Transporte());

        recepcionista.evaluarEnCadena(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("NO acepta excusas con motivo SUMINISTRO")
    void noAceptaSuministro() {
        Excusa excusa = new Excusa(empleado, new Suministro());

        recepcionista.evaluarEnCadena(excusa);

        assertFalse(excusa.isAceptada());
    }

    @Test
    @DisplayName("NO acepta excusas con motivo INVEROSIMIL")
    void noAceptaInverosimil() {
        Excusa excusa = new Excusa(empleado, new Inverosimil());

        recepcionista.evaluarEnCadena(excusa);

        assertFalse(excusa.isAceptada());
    }

    @Test
    @DisplayName("envía email al empleado al aceptar su excusa")
    void enviaEmailAlAceptar() {
        Excusa excusa = new Excusa(empleado, new Dormido());

        recepcionista.evaluarEnCadena(excusa);

        verify(emailSender).enviarEmail(
            eq("juan@mail.com"),
            eq("maria@excusassa.com"),
            contains("motivo demora"),
            contains("licencia fue aceptada")
        );
    }

    @Test
    @DisplayName("NO envía email cuando no puede procesar")
    void noEnviaEmailSiNoPuedeProcesar() {
        Excusa excusa = new Excusa(empleado, new Inverosimil());

        recepcionista.evaluarEnCadena(excusa);

        verify(emailSender, never()).enviarEmail(anyString(), anyString(), anyString(), anyString());
    }
}
