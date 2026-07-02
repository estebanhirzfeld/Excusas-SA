package ar.edu.davinci.excusassa.evaluacion;

import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.Dormido;
import ar.edu.davinci.excusassa.modelo.motivo.Motivo;
import ar.edu.davinci.excusassa.modelo.motivo.Transporte;
import ar.edu.davinci.excusassa.notificacion.EmailSender;

import java.util.Set;

public class Recepcionista extends Responsable {

    private static final Set<Motivo> MOTIVOS_PROCESABLES =
        Set.of(new Dormido(), new Transporte());

    public Recepcionista(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        super(nombre, email, nroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return MOTIVOS_PROCESABLES.contains(excusa.getMotivo());
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
