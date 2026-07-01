package ar.edu.utn.excusassa.evaluacion;

import ar.edu.utn.excusassa.notificacion.EmailSender;
import ar.edu.utn.excusassa.prontuario.AdministradorProntuarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CadenaDeResponsablesBuilder")
class CadenaDeResponsablesBuilderTest {

    @Mock
    private EmailSender emailSender;

    @Test
    @DisplayName("construye una cadena enlazando responsables en orden")
    void construyeCadenaEnOrden() {
        Responsable primero = new CadenaDeResponsablesBuilder()
            .agregarRecepcionista("R", "r@mail.com", 1, emailSender)
            .agregarSupervisora("S", "s@mail.com", 2, emailSender)
            .construir();

        assertNotNull(primero);
        assertNotNull(primero.getSiguiente());
    }

    @Test
    @DisplayName("agrega RechazadorDeExcusas al final automáticamente")
    void agregaRechazadorAlFinal() {
        Responsable primero = new CadenaDeResponsablesBuilder()
            .agregarRecepcionista("R", "r@mail.com", 1, emailSender)
            .construir();

        assertNotNull(primero.getSiguiente());
        assertInstanceOf(RechazadorDeExcusas.class, primero.getSiguiente());
    }

    @Test
    @DisplayName("cadena vacía retorna solo RechazadorDeExcusas")
    void cadenaVaciaRetornaRechazador() {
        Responsable primero = new CadenaDeResponsablesBuilder().construir();

        assertInstanceOf(RechazadorDeExcusas.class, primero);
    }

    @Test
    @DisplayName("cadena con 3 responsables enlaza correctamente")
    void tresResponsablesEnlazados() {
        Responsable primero = new CadenaDeResponsablesBuilder()
            .agregarRecepcionista("R", "r@mail.com", 1, emailSender)
            .agregarSupervisora("S", "s@mail.com", 2, emailSender)
            .agregarGerenteRRHH("G", "g@mail.com", 3, emailSender)
            .construir();

        assertNotNull(primero.getSiguiente());
        assertNotNull(primero.getSiguiente().getSiguiente());
        assertInstanceOf(RechazadorDeExcusas.class,
            primero.getSiguiente().getSiguiente().getSiguiente());
    }
}
