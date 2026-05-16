package com.esgaspar.programacao.service;

import com.esgaspar.programacao.exception.ResourceNotFoundException;
import com.esgaspar.programacao.mapper.VoluntarioMapper;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.esgaspar.programacao.repository.VoluntarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoluntarioService {

    private final VoluntarioRepository repository;
    private final VoluntarioMapper mapper;

    public VoluntarioDto find(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Voluntário não encontrado: " + id));
    }

    public List<VoluntarioDto> list() {
        return repository.findAllByOrderByNome().stream().map(mapper::toDto).toList();
    }

    public List<VoluntarioDto> listByNome(String nome) {
        String busca = "-".equals(nome) ? "" : nome.toUpperCase();
        return repository.findByNomeContainingIgnoreCase(busca).stream().map(mapper::toDto).toList();
    }

    @Transactional
    public VoluntarioDto save(VoluntarioDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id).ifPresent(v -> {
            v.setPrivilegios(new ArrayList<>());
            repository.saveAndFlush(v);
        });
        repository.deleteById(id);
    }
}
