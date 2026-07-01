package ar.edu.utn.excusassa.prontuario;

import java.util.ArrayList;
import java.util.List;

public class AdministradorProntuarios extends Observable {

    private final List<Prontuario> prontuarios;

    public AdministradorProntuarios() {
        super();
        this.prontuarios = new ArrayList<>();
    }

    public void crearProntuario(Prontuario prontuario) {
        prontuarios.add(prontuario);
        notificarObservadores(prontuario);
    }

    public List<Prontuario> getProntuarios() {
        return prontuarios;
    }
}
