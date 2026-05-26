package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.notificacion.EmailSender;

public class RechazadorDeExcusas extends Responsable {

    public RechazadorDeExcusas(EmailSender emailSender) {
        super("Sistema", "sistema@excusassa.com", 0, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return true; 
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.rechazar("Excusa rechazada: necesitamos pruebas contundentes");
    }
}
