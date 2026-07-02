package ar.edu.davinci.excusassa.evaluacion;

import ar.edu.davinci.excusassa.modelo.Empleado;
import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.*;
import ar.edu.davinci.excusassa.notificacion.EmailSender;
import ar.edu.davinci.excusassa.prontuario.AdministradorProntuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Cadena de Responsabilidad — Integración")
class ChainIntegracionTest {

    @Mock
    private EmailSender emailSender;

    private Responsable cadena;
    private Empleado empleado;
    private AdministradorProntuarios adminProntuarios;

    @BeforeEach
    void setUp() {
        adminProntuarios = new AdministradorProntuarios();

        cadena = new CadenaDeResponsablesBuilder()
            .agregarRecepcionista("María", "maria@mail.com", 10, emailSender)
            .agregarSupervisora("Laura", "laura@mail.com", 20, emailSender)
            .agregarGerenteRRHH("Carlos", "carlos@mail.com", 30, emailSender)
            .agregarCEO("Jefe", "jefe@mail.com", 1, emailSender, adminProntuarios)
            .construir();

        empleado = new Empleado("Pedro", "pedro@mail.com", 300);
    }

    @Test
    @DisplayName("DORMIDO → procesada por Recepcionista")
    void dormidoProcesadoPorRecepcionista() {
        Excusa excusa = new Excusa(empleado, new Dormido());

        cadena.revisarExcusa(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("TRANSPORTE → procesada por Recepcionista")
    void transporteProcesadoPorRecepcionista() {
        Excusa excusa = new Excusa(empleado, new Transporte());

        cadena.revisarExcusa(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("SUMINISTRO → fluye hasta SupervisoraDeArea")
    void suministroFlujeASupervisora() {
        Excusa excusa = new Excusa(empleado, new Suministro());

        cadena.revisarExcusa(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("FAMILIAR → fluye hasta SupervisoraDeArea")
    void familiarFlujeASupervisora() {
        Excusa excusa = new Excusa(empleado, new FamiliarACargo());

        cadena.revisarExcusa(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("INVEROSIMIL → fluye hasta GerenteRRHH que la acepta")
    void inverosimilFluyeAGerente() {
        Excusa excusa = new Excusa(empleado, new Inverosimil());

        cadena.revisarExcusa(excusa);

        assertTrue(excusa.isAceptada());
    }

    @Test
    @DisplayName("INVEROSIMIL procesada por GerenteRRHH, no llega al CEO")
    void inverosimilNOllegaAlCEO() {
        Excusa excusa = new Excusa(empleado, new Inverosimil());

        cadena.revisarExcusa(excusa);

        // El GerenteRRHH la procesa primero → no se crea prontuario
        assertEquals(0, adminProntuarios.getProntuarios().size());
    }

    @Test
    @DisplayName("cadena sin GerenteRRHH: INVEROSIMIL llega al CEO y crea prontuario")
    void sinGerenteLlegaAlCEO() {
        Responsable cadenaSinGerente = new CadenaDeResponsablesBuilder()
            .agregarRecepcionista("R", "r@mail.com", 10, emailSender)
            .agregarSupervisora("S", "s@mail.com", 20, emailSender)
            .agregarCEO("CEO", "ceo@mail.com", 1, emailSender, adminProntuarios)
            .construir();

        Excusa excusa = new Excusa(empleado, new Inverosimil());
        cadenaSinGerente.revisarExcusa(excusa);

        assertTrue(excusa.isAceptada());
        assertEquals("Aprobado por creatividad", excusa.getMensajeResultado());
        assertEquals(1, adminProntuarios.getProntuarios().size());
    }
}
