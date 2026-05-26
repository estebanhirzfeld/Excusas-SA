package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.notificacion.EmailSender;

public class SupervisoraDeArea extends Responsable {

    private static final String EDESUR_EMAIL = "EDESUR@mailfake.com.ar";

    public SupervisoraDeArea(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        super(nombre, email, nroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        String tipo = excusa.getMotivo().getTipo();
        return "SUMINISTRO".equals(tipo) || "FAMILIAR".equals(tipo);
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
        String tipo = excusa.getMotivo().getTipo();

        if ("SUMINISTRO".equals(tipo)) {
            emailSender.enviarEmail(
                EDESUR_EMAIL,
                this.getEmail(),
                "Consulta por corte de suministro",
                "Se reportó un corte de suministro en la zona del empleado "
                    + excusa.getEmpleado().getNombre()
            );
        } else {
            emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                this.getEmail(),
                "Consulta por familiar a cargo",
                "Estimado/a " + excusa.getEmpleado().getNombre()
                    + ", esperamos que todo está bien con su familiar."
            );
        }
    }
}
