package ar.edu.davinci.excusassa.repository;

import ar.edu.davinci.excusassa.prontuario.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
