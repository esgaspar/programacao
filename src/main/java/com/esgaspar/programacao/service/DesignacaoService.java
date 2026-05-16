package com.esgaspar.programacao.service;

import com.esgaspar.programacao.mapper.DesignacaoMapper;
import com.esgaspar.programacao.model.dto.DesignacaoDto;
import com.esgaspar.programacao.repository.DesignacaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignacaoService {

    private static final DateTimeFormatter URL_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final DesignacaoRepository repository;
    private final DesignacaoMapper mapper;


    public DesignacaoDto find(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new com.esgaspar.programacao.exception.ResourceNotFoundException(
                        "Designação não encontrada: " + id));
    }

    public List<DesignacaoDto> list() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public DesignacaoDto save(DesignacaoDto dto) {
        return mapper.toDto(repository.save(mapper.toEntityFromRequest(dto)));
    }

    @Transactional
    public List<DesignacaoDto> saveAll(List<DesignacaoDto> dtos) {
        LocalDate data = mapper.toEntityFromRequest(dtos.get(0)).getData();
        List<DesignacaoDto> existing = findByDate(data, data);

        repository.deleteAllInBatch(existing.stream().map(mapper::toEntityFromResponse).toList());

        return repository.saveAll(dtos.stream().map(mapper::toEntityFromRequest).toList())
                .stream().map(mapper::toDto).toList();
    }

    @Transactional
    public void deleteAll(List<DesignacaoDto> dtos) {
        repository.deleteAll(dtos.stream().map(mapper::toEntityFromResponse).toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<DesignacaoDto> findByDate(String inicioStr, String fimStr) {
        return findByDate(
                LocalDate.parse(inicioStr, URL_FORMAT),
                LocalDate.parse(fimStr, URL_FORMAT));
    }

    public List<DesignacaoDto> findByDate(LocalDate inicio, LocalDate fim) {
        return repository.findByData(inicio, fim).stream().map(mapper::toDto).toList();
    }

    public List<DesignacaoDto> findByMes(int mes) {
        return repository.findByMes(mes).stream().map(mapper::toDto).toList();
    }
}
