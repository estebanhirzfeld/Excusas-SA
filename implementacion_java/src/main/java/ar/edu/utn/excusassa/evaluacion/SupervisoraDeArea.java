package ar.edu.utn.excusassa.evaluacion;

import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.FamiliarACargo;
import ar.edu.utn.excusassa.modelo.motivo.Motivo;
import ar.edu.utn.excusassa.modelo.motivo.Suministro;
import ar.edu.utn.excusassa.notificacion.EmailSender;

import java.util.Set;

public class SupervisoraDeArea extends Responsable {

    private static final String EDESUR_EMAIL = "EDESUR@mailfake.com.ar";

    private static final Set<Motivo> MOTIVOS_PROCESABLES =
        Set.of(new Suministro(), new FamiliarACargo());

    public SupervisoraDeArea(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        super(nombre, email, nroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return MOTIVOS_PROCESABLES.contains(excusa.getMotivo());
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
        Motivo motivo = excusa.getMotivo();

        if (new Suministro().equals(motivo)) {
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
