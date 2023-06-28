package com.esgaspar.programacao.service;

import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.repository.PrivilegioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PrivilegioService {
    @Autowired
    PrivilegioRepository repository;

    public PrivilegioDto find(Long id) {
        Optional<Privilegio> privilegioOpt = repository.findById(id);
        Privilegio privilegio = privilegioOpt.orElse(Privilegio.builder().build());
        return privilegio.getDto();
    }

    public List<PrivilegioDto> list() {
        List<Privilegio> privilegioList = repository.findAllByOrderByOrdem();
        return privilegioList.stream().map(Privilegio::getDto).toList();
    }

    @Transactional
    public PrivilegioDto save(PrivilegioDto privilegioDto) {
        return repository.save(privilegioDto.getEntity()).getDto();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
