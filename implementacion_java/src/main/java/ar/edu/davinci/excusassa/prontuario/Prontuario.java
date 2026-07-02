package ar.edu.davinci.excusassa.prontuario;

import ar.edu.davinci.excusassa.modelo.Empleado;
import ar.edu.davinci.excusassa.modelo.Excusa;
import jakarta.persistence.*;

@Entity
@Table(name = "prontuarios")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "excusa_id", nullable = false)
    private Excusa excusa;

    protected Prontuario() {
        // Constructor vacío requerido por JPA
    }

    public Prontuario(Empleado empleado, Excusa excusa) {
        this.empleado = empleado;
        this.excusa = excusa;
    }

    public Long getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Excusa getExcusa() {
        return excusa;
    }
}
