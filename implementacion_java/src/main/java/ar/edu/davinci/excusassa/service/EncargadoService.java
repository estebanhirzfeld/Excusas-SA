package ar.edu.davinci.excusassa.service;

import ar.edu.davinci.excusassa.dto.EncargadoRequestDTO;
import ar.edu.davinci.excusassa.dto.EncargadoResponseDTO;
import ar.edu.davinci.excusassa.evaluacion.Responsable;
import ar.edu.davinci.excusassa.evaluacion.RechazadorDeExcusas;
import ar.edu.davinci.excusassa.evaluacion.estado.ModoEvaluacion;
import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.Motivo;
import ar.edu.davinci.excusassa.modelo.motivo.TipoMotivo;
import ar.edu.davinci.excusassa.notificacion.EmailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EncargadoService {

    private Responsable cadenaDeResponsables;
    private final EmailSender emailSender;

    public EncargadoService(Responsable cadenaDeResponsables, EmailSender emailSender) {
        this.cadenaDeResponsables = cadenaDeResponsables;
        this.emailSender = emailSender;
    }

    public List<EncargadoResponseDTO> listarEncargados() {
        List<EncargadoResponseDTO> resultado = new ArrayList<>();
        Responsable actual = cadenaDeResponsables;

        while (actual != null && !(actual instanceof RechazadorDeExcusas)) {
            resultado.add(mapearEncargado(actual));
            actual = actual.getSiguiente();
        }

        return resultado;
    }

    public EncargadoResponseDTO agregarEncargado(EncargadoRequestDTO dto) {
        List<Motivo> motivos = dto.getMotivosQuePuedeManejear().stream()
            .map(this::parsearMotivo)
            .toList();

        Set<Motivo> motivoSet = new HashSet<>(motivos);

        Responsable nuevoEncargado = new Responsable(
            dto.getNombre(), dto.getEmail(), dto.getNroLegajo(), emailSender
        ) {
            @Override
            protected boolean puedeProcesar(Excusa excusa) {
                return motivoSet.contains(excusa.getMotivo());
            }

            @Override
            protected void procesar(Excusa excusa) {
                excusa.aceptar();
                emailSender.enviarEmail(
                    excusa.getEmpleado().getEmail(),
                    this.getEmail(),
                    "Excusa procesada",
                    "Su excusa fue procesada por " + this.getNombre()
                );
            }
        };

        insertarAnteDelRechazador(nuevoEncargado);

        return mapearEncargado(nuevoEncargado);
    }

    public EncargadoResponseDTO cambiarModo(int nroLegajo, String modoStr) {
        ModoEvaluacion modo;
        try {
            modo = ModoEvaluacion.valueOf(modoStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Modo no válido: '" + modoStr + "'. Valores permitidos: " +
                    Arrays.toString(ModoEvaluacion.values()));
        }

        Responsable encargado = buscarPorLegajo(nroLegajo);
        encargado.setEstadoAnimo(modo.crearEstado());
        return mapearEncargado(encargado);
    }

    private Responsable buscarPorLegajo(int nroLegajo) {
        Responsable actual = cadenaDeResponsables;
        while (actual != null) {
            if (actual.getNroLegajo() == nroLegajo) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        throw new NoSuchElementException(
            "No se encontró encargado con legajo " + nroLegajo);
    }

    private void insertarAnteDelRechazador(Responsable nuevo) {
        if (cadenaDeResponsables instanceof RechazadorDeExcusas) {
            nuevo.setSiguiente(cadenaDeResponsables);
            cadenaDeResponsables = nuevo;
            return;
        }

        Responsable actual = cadenaDeResponsables;
        while (actual.getSiguiente() != null &&
               !(actual.getSiguiente() instanceof RechazadorDeExcusas)) {
            actual = actual.getSiguiente();
        }

        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
    }

    private EncargadoResponseDTO mapearEncargado(Responsable responsable) {
        String modo = responsable.getEstadoAnimo() != null
            ? responsable.getEstadoAnimo().getClass().getSimpleName().toUpperCase()
            : "SIN_ESTADO";

        return new EncargadoResponseDTO(
            responsable.getNombre(),
            responsable.getEmail(),
            responsable.getNroLegajo(),
            modo,
            List.of(),
            responsable.getExcusasProcesadas()
        );
    }

    private Motivo parsearMotivo(String motivo) {
        try {
            TipoMotivo tipo = TipoMotivo.valueOf(motivo.toUpperCase().trim());
            return tipo.crearMotivo();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Motivo no válido: '" + motivo + "'");
        }
    }
}
