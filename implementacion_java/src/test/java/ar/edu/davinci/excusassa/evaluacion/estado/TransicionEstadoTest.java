package ar.edu.davinci.excusassa.evaluacion.estado;

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
@DisplayName("Transiciones de Estado de Animo")
class TransicionEstadoTest {

    @Mock
    private EmailSender emailSender;

    private NormalTest.ResponsableStub responsable;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        responsable = new NormalTest.ResponsableStub("Test", "test@mail.com", 1, emailSender);
        responsable.setEstadoAnimo(new Normal());
        responsable.setPuedeProcesar(true);
        empleado = new Empleado("Juan", "juan@mail.com", 100);
    }

    @Test
    @DisplayName("< 5 excusas procesadas → debe ser Productivx")
    void menosDe5EsProductivx() {
        for (int i = 0; i < 3; i++) {
            responsable.evaluarEnCadena(new Excusa(empleado, new Dormido()));
        }
        actualizarEstadoPorExcusas(responsable);

        assertInstanceOf(Productivx.class, responsable.getEstadoAnimo());
    }

    @Test
    @DisplayName("entre 5 y 10 excusas procesadas → debe ser Normal")
    void entre5y10EsNormal() {
        for (int i = 0; i < 7; i++) {
            responsable.evaluarEnCadena(new Excusa(empleado, new Dormido()));
        }
        actualizarEstadoPorExcusas(responsable);

        assertInstanceOf(Normal.class, responsable.getEstadoAnimo());
    }

    @Test
    @DisplayName(">= 11 excusas procesadas → debe ser Vagx")
    void masde11EsVagx() {
        for (int i = 0; i < 12; i++) {
            responsable.evaluarEnCadena(new Excusa(empleado, new Dormido()));
        }
        actualizarEstadoPorExcusas(responsable);

        assertInstanceOf(Vagx.class, responsable.getEstadoAnimo());
    }

    @Test
    @DisplayName("tomar café → cambia a Productivx")
    void cafeAProductivx() {
        responsable.setEstadoAnimo(new Vagx());

        tomarCafe(responsable);

        assertInstanceOf(Productivx.class, responsable.getEstadoAnimo());
    }

    @Test
    @DisplayName("break de 10-15 min → cambia a Normal")
    void breakANormal() {
        responsable.setEstadoAnimo(new Vagx());

        tomarBreak(responsable, 12);

        assertInstanceOf(Normal.class, responsable.getEstadoAnimo());
    }

    @Test
    @DisplayName("break > 15 min → no surte efecto")
    void breakLargoNoSurteEfecto() {
        responsable.setEstadoAnimo(new Vagx());
        EstadoAnimo estadoAntes = responsable.getEstadoAnimo();

        tomarBreak(responsable, 20);

        assertSame(estadoAntes, responsable.getEstadoAnimo());
    }

    private void actualizarEstadoPorExcusas(NormalTest.ResponsableStub r) {
        int procesadas = r.getExcusasProcesadas();
        if (procesadas < 5) {
            r.setEstadoAnimo(new Productivx());
        } else if (procesadas <= 10) {
            r.setEstadoAnimo(new Normal());
        } else {
            r.setEstadoAnimo(new Vagx());
        }
    }

    private void tomarCafe(NormalTest.ResponsableStub r) {
        r.setEstadoAnimo(new Productivx());
    }

    private void tomarBreak(NormalTest.ResponsableStub r, int minutos) {
        if (minutos >= 10 && minutos <= 15) {
            r.setEstadoAnimo(new Normal());
        }
    }
}
