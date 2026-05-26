package ar.edu.utn.excusassa.prontuario;

import java.util.ArrayList;
import java.util.List;

public class AdministradorProntuarios implements Observable {

    private final List<Prontuario> prontuarios;
    private final List<ObservadorProntuario> observadores;

    public AdministradorProntuarios() {
        this.prontuarios = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    public void crearProntuario(Prontuario prontuario) {
        prontuarios.add(prontuario);
        notificarObservadores(prontuario);
    }

    @Override
    public void agregarObservador(ObservadorProntuario observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorProntuario observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(Prontuario prontuario) {
        for (ObservadorProntuario obs : observadores) {
            obs.notificar(prontuario);
        }
    }

    public List<Prontuario> getProntuarios() {
        return prontuarios;
    }
}
