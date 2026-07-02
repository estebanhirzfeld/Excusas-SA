package ar.edu.davinci.excusassa.evaluacion;

import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.Inverosimil;
import ar.edu.davinci.excusassa.modelo.motivo.Motivo;
import ar.edu.davinci.excusassa.notificacion.EmailSender;
import ar.edu.davinci.excusassa.prontuario.AdministradorProntuarios;
import ar.edu.davinci.excusassa.prontuario.Prontuario;

import java.util.Set;

public class CEO extends Responsable {

    private final AdministradorProntuarios adminProntuarios;

    private static final Set<Motivo> MOTIVOS_PROCESABLES =
        Set.of(new Inverosimil());

    public CEO(String nombre, String email, int nroLegajo, EmailSender emailSender,
               AdministradorProntuarios adminProntuarios) {
        super(nombre, email, nroLegajo, emailSender);
        this.adminProntuarios = adminProntuarios;
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return MOTIVOS_PROCESABLES.contains(excusa.getMotivo());
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar("Aprobado por creatividad");

        emailSender.enviarEmail(
            excusa.getEmpleado().getEmail(),
            this.getEmail(),
            "Aprobado por creatividad",
            "Estimado/a " + excusa.getEmpleado().getNombre()
                + ", su excusa fue aprobada por creatividad."
        );

        Prontuario prontuario = new Prontuario(excusa.getEmpleado(), excusa);
        adminProntuarios.crearProntuario(prontuario);
    }
}
