package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.*;
import ar.edu.utn.excusassa.notificacion.EmailSender;
import ar.edu.utn.excusassa.prontuario.AdministradorProntuarios;
import ar.edu.utn.excusassa.prontuario.Prontuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CEO (ConcreteHandler)")
class CEOTest {

    @Mock
    private EmailSender emailSender;

    private AdministradorProntuarios adminProntuarios;
    private CEO ceo;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        adminProntuarios = new AdministradorProntuarios();
        ceo = new CEO("Jefe", "jefe@excusassa.com", 1, emailSender, adminProntuarios);
        empleado = new Empleado("Pedro", "pedro@mail.com", 300);
    }

    @Test
    @DisplayName("acepta excusas con motivo INVEROSIMIL")
    void aceptaInverosimil() {
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());

        ceo.evaluarEnCadena(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("responde 'Aprobado por creatividad'")
    void respondeAprobadoPorCreatividad() {
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());

        ceo.evaluarEnCadena(excusa);

        assertEquals("Aprobado por creatividad", excusa.getMensajeResultado());
    }

    @Test
    @DisplayName("crea prontuario al procesar excusa inverosímil")
    void creaProntuario() {
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());

        ceo.evaluarEnCadena(excusa);

        assertEquals(1, adminProntuarios.getProntuarios().size());
        Prontuario prontuario = adminProntuarios.getProntuarios().get(0);
        assertSame(empleado, prontuario.getEmpleado());
        assertSame(excusa, prontuario.getExcusa());
    }

    @Test
    @DisplayName("NO acepta excusas con motivo DORMIDO")
    void noAceptaDormido() {
        Excusa excusa = new Excusa(empleado, new MotivoDormido());

        ceo.evaluarEnCadena(excusa);

        assertFalse(excusa.isAceptada());
    }
}
