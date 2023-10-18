package com.esgaspar.programacao.service;

import com.esgaspar.programacao.model.Designacao;
import com.esgaspar.programacao.model.dto.DesignacaoDto;
import com.esgaspar.programacao.repository.DesignacaoRepository;
import com.esgaspar.programacao.util.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class DesignacaoService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    DesignacaoRepository repository;

    public DesignacaoDto find(Long id) {
        Optional<Designacao> designacaoOpt = repository.findById(id);
        Designacao designacao = designacaoOpt.orElse(Designacao.builder().build());
        return designacao.getDto();
    }

    public List<DesignacaoDto> list() {
        List<Designacao> designacaoList = repository.findAll();
        return designacaoList.stream().map(Designacao::getDto).toList();
    }

    @Transactional
    public DesignacaoDto save(DesignacaoDto designacaoDto) {
        return repository.save(designacaoDto.getEntity()).getDto();
    }

    @Transactional
    public List<DesignacaoDto> saveAll(List<DesignacaoDto> designacaoDtoList) {

        LocalDate data = designacaoDtoList.get(0).getEntity().getData();
        List<DesignacaoDto> listByDate = findByDate(data, data);

        repository.deleteAllInBatch(listByDate.stream().map(
                DesignacaoDto::getEntityPrint).toList());

        return repository.saveAll(designacaoDtoList.stream().map(
                        DesignacaoDto::getEntity).toList())
                .stream().map(Designacao::getDto).toList();
    }


    @Transactional
    public void deleteAll(List<DesignacaoDto> designacaoDtoList) {
        repository.deleteAll(designacaoDtoList.stream().map(
                DesignacaoDto::getEntityPrint).toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @SneakyThrows
    public List<DesignacaoDto> findByDate(String inicioStr, String fimStr) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate inicio = Utils.DateUtils.asLocalDate(formato.parse(inicioStr));
        LocalDate fim = Utils.DateUtils.asLocalDate(formato.parse(fimStr));

        return findByDate(inicio, fim);
    }

    public List<DesignacaoDto> findByDate(LocalDate inicio, LocalDate fim) {
        return repository.findByData(inicio, fim).stream().map(Designacao::getDto).toList();
    }

    public List<DesignacaoDto> findByMes(int mes) {
        return repository.findByMes(mes).stream().map(Designacao::getDto).toList();
    }
}
