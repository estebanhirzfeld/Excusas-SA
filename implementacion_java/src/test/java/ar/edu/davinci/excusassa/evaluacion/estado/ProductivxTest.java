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
@DisplayName("Estado Productivx")
class ProductivxTest {

    @Mock
    private EmailSender emailSender;

    private NormalTest.ResponsableStub responsable;
    private Excusa excusa;

    @BeforeEach
    void setUp() {
        responsable = new NormalTest.ResponsableStub("Test", "test@mail.com", 1, emailSender);
        responsable.setEstadoAnimo(new Productivx());
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        excusa = new Excusa(empleado, new Dormido());
    }

    @Test
    @DisplayName("si la PUEDE procesar, la procesa")
    void procesaSiPuede() {
        responsable.setPuedeProcesar(true);

        responsable.revisarExcusa(excusa);

        assertTrue(responsable.fueProcesado());
        assertTrue(responsable.fueEvaluadoEnCadena());
    }

    @Test
    @DisplayName("si NO la puede procesar, pero HAY siguiente, la deriva")
    void derivaSiNoPuedeYHaysiguiente() {
        responsable.setPuedeProcesar(false);
        NormalTest.ResponsableStub siguiente = new NormalTest.ResponsableStub("Sig", "sig@mail.com", 2, emailSender);
        siguiente.setPuedeProcesar(true);
        responsable.setSiguiente(siguiente);

        responsable.revisarExcusa(excusa);

        assertFalse(responsable.fueProcesado());
        assertFalse(responsable.fueEvaluadoEnCadena());
        assertTrue(siguiente.fueProcesado());
    }

    @Test
    @DisplayName("si NO la puede procesar y NO hay siguiente, LA PROCESA IGUAL")
    void procesaSiNoPuedeYNoHaySiguiente() {
        responsable.setPuedeProcesar(false);

        responsable.revisarExcusa(excusa);

        assertTrue(responsable.fueProcesado());
        assertFalse(responsable.fueEvaluadoEnCadena());
    }
}
