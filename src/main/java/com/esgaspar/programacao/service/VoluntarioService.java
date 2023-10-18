package com.esgaspar.programacao.service;

import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.Voluntario;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.esgaspar.programacao.repository.VoluntarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VoluntarioService {
    @Autowired
    VoluntarioRepository repository;

    public VoluntarioDto find(Long id) {
        Optional<Voluntario> voluntarioOpt = repository.findById(id);

        Voluntario voluntario = voluntarioOpt.orElse(Voluntario.builder().build());
        return voluntario.getDto();
    }

    public List<VoluntarioDto> list() {
        List<Voluntario> voluntarioList = repository.findAllByOrderByNome();
        return voluntarioList.stream().map(Voluntario::getDto).toList();
    }

    public List<VoluntarioDto> listByNome(String nome) {
        if (nome.equalsIgnoreCase("-")) {
            nome = "";
        }

        List<Voluntario> voluntarioList = repository.findByNomeContainingIgnoreCase(nome.toUpperCase());
        return voluntarioList.stream().map(Voluntario::getDto).toList();
    }

    @Transactional
    public VoluntarioDto save(VoluntarioDto voluntarioDto) {
        return repository.save(voluntarioDto.getEntity()).getDto();
    }

    public void delete(Long id) {
        Optional<Voluntario> opt = repository.findById(id);

        opt.ifPresent(voluntario -> {
            voluntario.setPrivilegios(new ArrayList<>());
            repository.saveAndFlush(voluntario);

        });


        repository.deleteById(id);
    }

}
