package ar.edu.utn.excusassa.evaluacion;

import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.modelo.motivo.Dormido;
import ar.edu.utn.excusassa.modelo.motivo.Motivo;
import ar.edu.utn.excusassa.modelo.motivo.Transporte;
import ar.edu.utn.excusassa.notificacion.EmailSender;

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
