package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.notificacion.EmailSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CadenaDeResponsablesBuilder")
class ChainOfBuilderTest {

    @Mock
    private EmailSender emailSender;

    @Test
    @DisplayName("construye una cadena enlazando responsables en orden")
    void construyeCadenaEnOrden() {
        Recepcionista recepcionista = new Recepcionista("R", "r@mail.com", 1, emailSender);
        SupervisoraDeArea supervisora = new SupervisoraDeArea("S", "s@mail.com", 2, emailSender);

        Responsable primero = new ChainOfBuilder()
            .agregar(recepcionista)
            .agregar(supervisora)
            .construir();

        assertSame(recepcionista, primero);
        assertSame(supervisora, recepcionista.getSiguiente());
    }

    @Test
    @DisplayName("agrega RechazadorDeExcusas al final automáticamente")
    void agregaRechazadorAlFinal() {
        Recepcionista recepcionista = new Recepcionista("R", "r@mail.com", 1, emailSender);

        Responsable primero = new ChainOfBuilder()
            .agregar(recepcionista)
            .construir();

        assertSame(recepcionista, primero);
        assertNotNull(recepcionista.getSiguiente());
        assertInstanceOf(RechazadorDeExcusas.class, recepcionista.getSiguiente());
    }

    @Test
    @DisplayName("cadena vacía retorna solo RechazadorDeExcusas")
    void cadenaVaciaRetornaRechazador() {
        Responsable primero = new ChainOfBuilder().construir();

        assertInstanceOf(RechazadorDeExcusas.class, primero);
    }

    @Test
    @DisplayName("cadena con 3 responsables enlaza correctamente")
    void treResponsablesEnlazados() {
        Recepcionista r = new Recepcionista("R", "r@mail.com", 1, emailSender);
        SupervisoraDeArea s = new SupervisoraDeArea("S", "s@mail.com", 2, emailSender);
        GerenteRRHH g = new GerenteRRHH("G", "g@mail.com", 3, emailSender);

        new ChainOfBuilder()
            .agregar(r)
            .agregar(s)
            .agregar(g)
            .construir();

        assertSame(s, r.getSiguiente());
        assertSame(g, s.getSiguiente());
        assertInstanceOf(RechazadorDeExcusas.class, g.getSiguiente());
    }
}
