package ar.edu.utn.excusassa.evaluacion;

import ar.edu.utn.excusassa.notificacion.EmailSender;
import ar.edu.utn.excusassa.prontuario.AdministradorProntuarios;

import java.util.ArrayList;
import java.util.List;

public class CadenaDeResponsablesBuilder {

    private final List<Responsable> responsables;

    public CadenaDeResponsablesBuilder() {
        this.responsables = new ArrayList<>();
    }

    public CadenaDeResponsablesBuilder agregarRecepcionista(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        responsables.add(new Recepcionista(nombre, email, nroLegajo, emailSender));
        return this;
    }

    public CadenaDeResponsablesBuilder agregarSupervisora(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        responsables.add(new SupervisoraDeArea(nombre, email, nroLegajo, emailSender));
        return this;
    }

    public CadenaDeResponsablesBuilder agregarGerenteRRHH(String nombre, String email, int nroLegajo, EmailSender emailSender) {
        responsables.add(new GerenteRRHH(nombre, email, nroLegajo, emailSender));
        return this;
    }

    public CadenaDeResponsablesBuilder agregarCEO(String nombre, String email, int nroLegajo, EmailSender emailSender,
                                                   AdministradorProntuarios adminProntuarios) {
        responsables.add(new CEO(nombre, email, nroLegajo, emailSender, adminProntuarios));
        return this;
    }

    public Responsable construir() {

        RechazadorDeExcusas rechazador = new RechazadorDeExcusas(null);
        responsables.add(rechazador);

        for (int i = 0; i < responsables.size() - 1; i++) {
            responsables.get(i).setSiguiente(responsables.get(i + 1));
        }

        return responsables.get(0);
    }
}
