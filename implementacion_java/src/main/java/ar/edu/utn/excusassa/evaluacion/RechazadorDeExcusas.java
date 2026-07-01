package ar.edu.utn.excusassa.evaluacion;

import ar.edu.utn.excusassa.evaluacion.estado.EstadoAnimo;
import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.notificacion.EmailSender;

public class RechazadorDeExcusas extends Responsable {

    public RechazadorDeExcusas(EmailSender emailSender) {
        super("Sistema", "sistema@excusassa.com", 0, emailSender);
    }

    @Override
    public void revisarExcusa(Excusa excusa) {
        procesar(excusa);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return true;
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.rechazar("Excusa rechazada: necesitamos pruebas contundentes");
    }

    @Override
    public void derivarAlSiguiente(Excusa excusa) {
        // Null Object — fin de la cadena, no deriva a nadie
    }

    @Override
    public void setSiguiente(Responsable siguiente) {
        // Null Object — no tiene siguiente
    }

    @Override
    public void setEstadoAnimo(EstadoAnimo estadoAnimo) {
        // Null Object — no tiene estado de ánimo
    }
}
