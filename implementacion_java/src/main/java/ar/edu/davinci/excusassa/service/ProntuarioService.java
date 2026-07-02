package ar.edu.davinci.excusassa.service;

import ar.edu.davinci.excusassa.dto.ProntuarioResponseDTO;
import ar.edu.davinci.excusassa.mapper.ProntuarioMapper;
import ar.edu.davinci.excusassa.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProntuarioService {

    private final ProntuarioRepository repository;
    private final ProntuarioMapper mapper;

    public ProntuarioService(ProntuarioRepository repository, ProntuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProntuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
            .map(mapper::toDTO)
            .toList();
    }
}
