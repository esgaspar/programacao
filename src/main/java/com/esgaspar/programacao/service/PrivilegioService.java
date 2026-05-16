package com.esgaspar.programacao.service;

import com.esgaspar.programacao.exception.ResourceNotFoundException;
import com.esgaspar.programacao.mapper.PrivilegioMapper;
import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.repository.PrivilegioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegioService {

    private final PrivilegioRepository repository;
    private final PrivilegioMapper mapper;

    public PrivilegioDto find(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Privilégio não encontrado: " + id));
    }

    public List<PrivilegioDto> list() {
        return repository.findAllByOrderByOrdem().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public PrivilegioDto save(PrivilegioDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    public List<PrivilegioDto> saveAll(List<PrivilegioDto> dtos) {
        return repository.saveAll(dtos.stream().map(mapper::toEntity).toList())
                .stream().map(mapper::toDto).toList();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
