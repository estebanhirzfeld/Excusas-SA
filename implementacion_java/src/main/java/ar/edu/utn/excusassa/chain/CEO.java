package ar.edu.utn.excusassa.chain;

import ar.edu.utn.excusassa.modelo.Excusa;
import ar.edu.utn.excusassa.notificacion.EmailSender;
import ar.edu.utn.excusassa.prontuario.AdministradorProntuarios;
import ar.edu.utn.excusassa.prontuario.Prontuario;

public class CEO extends Responsable {

    private final AdministradorProntuarios adminProntuarios;

    public CEO(String nombre, String email, int nroLegajo, EmailSender emailSender,
               AdministradorProntuarios adminProntuarios) {
        super(nombre, email, nroLegajo, emailSender);
        this.adminProntuarios = adminProntuarios;
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return "INVEROSIMIL".equals(excusa.getMotivo().getTipo());
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar("Aprobado por creatividad");

        Prontuario prontuario = new Prontuario(excusa.getEmpleado(), excusa);
        adminProntuarios.crearProntuario(prontuario);
    }
}
