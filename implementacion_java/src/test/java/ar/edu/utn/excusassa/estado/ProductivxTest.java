package ar.edu.utn.excusassa.estado;

import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.MotivoDormido;
import ar.edu.utn.excusassa.notificacion.EmailSender;
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
        excusa = new Excusa(empleado, new MotivoDormido());
    }

    @Test
    @DisplayName("evalúa normalmente si puede procesar")
    void evaluaNormalmente() {
        responsable.setPuedeProcesar(true);

        responsable.revisarExcusa(excusa);

        assertTrue(responsable.fueProcesado());
    }

    @Test
    @DisplayName("si no puede procesar y hay siguiente, deriva")
    void derivaSiHaySiguiente() {
        responsable.setPuedeProcesar(false);
        NormalTest.ResponsableStub siguiente = new NormalTest.ResponsableStub("Sig", "sig@mail.com", 2, emailSender);
        siguiente.setPuedeProcesar(true);
        responsable.setSiguiente(siguiente);

        responsable.revisarExcusa(excusa);

        assertFalse(responsable.fueProcesado());
        assertTrue(siguiente.fueProcesado());
    }

    @Test
    @DisplayName("si no puede procesar y NO hay siguiente, intenta de todas formas")
    void intentaSiNoHaySiguiente() {
        responsable.setPuedeProcesar(false);
        // No seteamos siguiente → no hay siguiente

        responsable.revisarExcusa(excusa);

        // Productivx fuerza el procesamiento aunque no pueda
        assertTrue(responsable.fueProcesado());
    }
}
