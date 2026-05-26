package ar.edu.utn.excusassa.prontuario;

import ar.edu.utn.excusassa.chain.*;
import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.MotivoInverosimil;
import ar.edu.utn.excusassa.notificacion.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Observer — Integración (CEO + Prontuario + Observadores)")
class ObserverIntegracionTest {

    @Mock
    private EmailSender emailSender;

    private AdministradorProntuarios admin;
    private List<Prontuario> prontuariosNotificados;

    @BeforeEach
    void setUp() {
        admin = new AdministradorProntuarios();
        prontuariosNotificados = new ArrayList<>();
    }

    @Test
    @DisplayName("múltiples observadores son notificados cuando el CEO crea un prontuario")
    void multiplesObservadoresNotificados() {
        // Registrar 3 observadores (equipo de dirección)
        admin.agregarObservador(p -> prontuariosNotificados.add(p));
        admin.agregarObservador(p -> prontuariosNotificados.add(p));
        admin.agregarObservador(p -> prontuariosNotificados.add(p));

        // Cadena directa al CEO (sin GerenteRRHH para que llegue)
        Responsable cadena = new ChainOfBuilder()
            .agregar(new CEO("Jefe", "jefe@mail.com", 1, emailSender, admin))
            .construir();

        Empleado empleado = new Empleado("Ana", "ana@mail.com", 200);
        Excusa excusa = new Excusa(empleado, new MotivoInverosimil());

        cadena.revisarExcusa(excusa);

        // 3 observadores notificados
        assertEquals(3, prontuariosNotificados.size());
        // Todos recibieron el mismo prontuario
        Prontuario primero = prontuariosNotificados.get(0);
        assertSame(empleado, primero.getEmpleado());
        assertSame(excusa, primero.getExcusa());
    }
}
