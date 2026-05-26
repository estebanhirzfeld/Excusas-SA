package ar.edu.utn.excusassa.prontuario;

public interface Observable {

    void agregarObservador(ObservadorProntuario observador);

    void eliminarObservador(ObservadorProntuario observador);

    void notificarObservadores(Prontuario prontuario);
}
