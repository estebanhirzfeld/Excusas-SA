package ar.edu.utn.excusassa.estado;

import ar.edu.utn.excusassa.chain.Responsable;
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
@DisplayName("Estado Normal")
class NormalTest {

    @Mock
    private EmailSender emailSender;

    private ResponsableStub responsable;
    private Excusa excusa;

    @BeforeEach
    void setUp() {
        responsable = new ResponsableStub("Test", "test@mail.com", 1, emailSender);
        responsable.setEstadoAnimo(new Normal());
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        excusa = new Excusa(empleado, new MotivoDormido());
    }

    @Test
    @DisplayName("delega a evaluarEnCadena cuando el estado es Normal")
    void delegaAEvaluarEnCadena() {
        responsable.setPuedeProcesar(true);

        responsable.revisarExcusa(excusa);

        assertTrue(responsable.fueEvaluadoEnCadena());
    }

    @Test
    @DisplayName("si puede procesar, la excusa se procesa")
    void procesaSiPuede() {
        responsable.setPuedeProcesar(true);

        responsable.revisarExcusa(excusa);

        assertTrue(responsable.fueProcesado());
    }

    @Test
    @DisplayName("si no puede procesar, deriva al siguiente")
    void derivaSiNoPuede() {
        responsable.setPuedeProcesar(false);
        ResponsableStub siguiente = new ResponsableStub("Sig", "sig@mail.com", 2, emailSender);
        siguiente.setPuedeProcesar(true);
        responsable.setSiguiente(siguiente);

        responsable.revisarExcusa(excusa);

        assertFalse(responsable.fueProcesado());
        assertTrue(siguiente.fueProcesado());
    }

    static class ResponsableStub extends Responsable {
        private boolean puedeProcesar = false;
        private boolean procesado = false;
        private boolean evaluadoEnCadena = false;

        ResponsableStub(String nombre, String email, int nroLegajo, EmailSender emailSender) {
            super(nombre, email, nroLegajo, emailSender);
        }

        void setPuedeProcesar(boolean valor) {
            this.puedeProcesar = valor;
        }

        boolean fueProcesado() {
            return procesado;
        }

        boolean fueEvaluadoEnCadena() {
            return evaluadoEnCadena;
        }

        @Override
        public void evaluarEnCadena(Excusa excusa) {
            this.evaluadoEnCadena = true;
            super.evaluarEnCadena(excusa);
        }

        @Override
        protected boolean puedeProcesar(Excusa excusa) {
            return puedeProcesar;
        }

        @Override
        protected void procesar(Excusa excusa) {
            procesado = true;
            excusa.aceptar();
        }
    }
}
