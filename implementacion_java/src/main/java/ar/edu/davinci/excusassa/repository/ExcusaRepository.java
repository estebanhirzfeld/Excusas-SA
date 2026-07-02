package ar.edu.davinci.excusassa.repository;

import ar.edu.davinci.excusassa.modelo.Excusa;
import ar.edu.davinci.excusassa.modelo.motivo.TipoMotivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExcusaRepository extends JpaRepository<Excusa, Long> {

    List<Excusa> findByEmpleadoNroLegajo(int nroLegajo);

    List<Excusa> findByAceptadaFalse();

    List<Excusa> findByTipoMotivo(TipoMotivo tipoMotivo);

    List<Excusa> findByEncargadoQueProceso(String encargado);

    List<Excusa> findByFechaCreacionBetween(LocalDateTime desde, LocalDateTime hasta);

    List<Excusa> findByEmpleadoNroLegajoAndFechaCreacionBetween(
        int nroLegajo, LocalDateTime desde, LocalDateTime hasta);

    List<Excusa> findByFechaCreacionBefore(LocalDateTime fechaLimite);

    void deleteByFechaCreacionBefore(LocalDateTime fechaLimite);

    long countByFechaCreacionBefore(LocalDateTime fechaLimite);
}
