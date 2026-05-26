package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.notificacion.EmailSender;

public class GerenteRRHH extends Responsable {

    public GerenteRRHH(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        super(nombre, email, nroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return "INVEROSIMIL".equals(excusa.getMotivo().getTipo());
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
    }
}
