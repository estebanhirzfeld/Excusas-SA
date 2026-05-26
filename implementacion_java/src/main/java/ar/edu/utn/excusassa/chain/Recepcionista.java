package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.notificacion.EmailSender;

public class Recepcionista extends Responsable {

    public Recepcionista(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        super(nombre, email, nroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        String tipo = excusa.getMotivo().getTipo();
        return "DORMIDO".equals(tipo) || "TRANSPORTE".equals(tipo);
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
        emailSender.enviarEmail(
            excusa.getEmpleado().getEmail(),
            this.getEmail(),
            "motivo demora",
            "Estimado/a " + excusa.getEmpleado().getNombre() + ", la licencia fue aceptada."
        );
    }
}
