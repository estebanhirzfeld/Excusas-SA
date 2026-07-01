package ar.edu.utn.excusassa.modelo.motivo;

public abstract class Motivo {

    public abstract String getNombre();

    public abstract String getDescripcion();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motivo motivo = (Motivo) o;
        return getNombre().equals(motivo.getNombre());
    }

    @Override
    public final int hashCode() {
        return getNombre().hashCode();
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
