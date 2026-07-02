package ar.edu.davinci.excusassa.modelo;

import ar.edu.davinci.excusassa.modelo.motivo.Motivo;
import ar.edu.davinci.excusassa.modelo.motivo.TipoMotivo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "excusas")
public class Excusa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_motivo", nullable = false)
    private TipoMotivo tipoMotivo;

    private boolean aceptada;
    private String mensajeResultado;

    @Column(name = "encargado_que_proceso")
    private String encargadoQueProceso;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Transient
    private Motivo motivo;

    protected Excusa() {
        // Constructor vacío requerido por JPA
    }

    public Excusa(Empleado empleado, Motivo motivo) {
        this.empleado = empleado;
        this.motivo = motivo;
        this.aceptada = false;
        this.mensajeResultado = null;
        this.fechaCreacion = LocalDateTime.now();
        this.tipoMotivo = resolverTipoMotivo(motivo);
    }

    private TipoMotivo resolverTipoMotivo(Motivo motivo) {
        for (TipoMotivo tipo : TipoMotivo.values()) {
            if (tipo.crearMotivo().equals(motivo)) {
                return tipo;
            }
        }
        return null;
    }

    public void aceptar() {
        this.aceptada = true;
        this.mensajeResultado = "Excusa aceptada";
    }

    public void aceptar(String mensaje) {
        this.aceptada = true;
        this.mensajeResultado = mensaje;
    }

    public void rechazar(String motivo) {
        this.aceptada = false;
        this.mensajeResultado = motivo;
    }

    public Long getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Motivo getMotivo() {
        if (motivo == null && tipoMotivo != null) {
            motivo = tipoMotivo.crearMotivo();
        }
        return motivo;
    }

    public TipoMotivo getTipoMotivo() {
        return tipoMotivo;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public String getEncargadoQueProceso() {
        return encargadoQueProceso;
    }

    public void setEncargadoQueProceso(String encargadoQueProceso) {
        this.encargadoQueProceso = encargadoQueProceso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
