package ar.edu.utn.excusassa.evaluacion.estado;

import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.Dormido;
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
@DisplayName("Estado Vagx")
class VagxTest {

    @Mock
    private EmailSender emailSender;

    private NormalTest.ResponsableStub responsable;
    private Excusa excusa;

    @BeforeEach
    void setUp() {
        responsable = new NormalTest.ResponsableStub("Test", "test@mail.com", 1, emailSender);
        responsable.setEstadoAnimo(new Vagx());
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        excusa = new Excusa(empleado, new Dormido());
    }

    @Test
    @DisplayName("NO evalúa la excusa, la deriva directamente")
    void noEvalua() {
        NormalTest.ResponsableStub siguiente = new NormalTest.ResponsableStub("Sig", "sig@mail.com", 2, emailSender);
        siguiente.setPuedeProcesar(true);
        responsable.setSiguiente(siguiente);

        responsable.revisarExcusa(excusa);

        assertFalse(responsable.fueProcesado());
        assertFalse(responsable.fueEvaluadoEnCadena());
    }

    @Test
    @DisplayName("envía email al CTO antes de derivar")
    void enviaEmailAlCTO() {
        NormalTest.ResponsableStub siguiente = new NormalTest.ResponsableStub("Sig", "sig@mail.com", 2, emailSender);
        siguiente.setPuedeProcesar(true);
        responsable.setSiguiente(siguiente);

        responsable.revisarExcusa(excusa);

        verify(emailSender).enviarEmail(
            eq("cto@excusassa.com"),
            eq("test@mail.com"),
            contains("Excusa derivada"),
            anyString()
        );
    }

    @Test
    @DisplayName("si no hay siguiente, la excusa queda sin procesar")
    void sinSiguienteNoHaceNada() {
        responsable.revisarExcusa(excusa);

        assertFalse(responsable.fueProcesado());
    }
}
