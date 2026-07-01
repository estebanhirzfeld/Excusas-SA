package ar.edu.utn.excusassa.prontuario;

import ar.edu.utn.excusassa.modelo.Empleado;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.Inverosimil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("AdministradorProntuarios (Subject)")
class AdministradorProntuariosTest {

    private AdministradorProntuarios admin;

    @Mock
    private ObservadorProntuario observador1;

    @Mock
    private ObservadorProntuario observador2;

    @BeforeEach
    void setUp() {
        admin = new AdministradorProntuarios();
    }

    @Test
    @DisplayName("crea y almacena un prontuario")
    void creaYAlmacena() {
        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        Excusa excusa = new Excusa(empleado, new Inverosimil());
        Prontuario prontuario = new Prontuario(empleado, excusa);

        admin.crearProntuario(prontuario);

        assertEquals(1, admin.getProntuarios().size());
        assertSame(prontuario, admin.getProntuarios().get(0));
    }

    @Test
    @DisplayName("notifica a los observadores al crear prontuario")
    void notificaObservadores() {
        admin.agregarObservador(observador1);
        admin.agregarObservador(observador2);

        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        Excusa excusa = new Excusa(empleado, new Inverosimil());
        Prontuario prontuario = new Prontuario(empleado, excusa);

        admin.crearProntuario(prontuario);

        verify(observador1).notificar(prontuario);
        verify(observador2).notificar(prontuario);
    }

    @Test
    @DisplayName("eliminar un observador no lo notifica más")
    void eliminaObservador() {
        admin.agregarObservador(observador1);
        admin.agregarObservador(observador2);
        admin.eliminarObservador(observador1);

        Empleado empleado = new Empleado("Juan", "juan@mail.com", 100);
        Excusa excusa = new Excusa(empleado, new Inverosimil());
        Prontuario prontuario = new Prontuario(empleado, excusa);

        admin.crearProntuario(prontuario);

        verify(observador2).notificar(prontuario);
    }
}
