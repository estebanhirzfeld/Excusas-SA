package ar.edu.davinci.excusassa.evaluacion;

import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.Inverosimil;
import ar.edu.davinci.excusassa.modelo.motivo.Motivo;
import ar.edu.davinci.excusassa.notificacion.EmailSender;

import java.util.Set;

public class GerenteRRHH extends Responsable {

    private static final Set<Motivo> MOTIVOS_PROCESABLES =
        Set.of(new Inverosimil());

    public GerenteRRHH(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        super(nombre, email, nroLegajo, emailSender);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return MOTIVOS_PROCESABLES.contains(excusa.getMotivo());
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
    }
}
