package ar.edu.davinci.excusassa.prontuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private final List<ObservadorProntuario> observadores;

    protected Observable() {
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(ObservadorProntuario observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(ObservadorProntuario observador) {
        observadores.remove(observador);
    }

    protected void notificarObservadores(Prontuario prontuario) {
        for (ObservadorProntuario obs : observadores) {
            obs.notificar(prontuario);
        }
    }
}
