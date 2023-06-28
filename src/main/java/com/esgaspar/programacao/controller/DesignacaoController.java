package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.DesignacaoDto;
import com.esgaspar.programacao.service.DesignacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("designacao")
public class DesignacaoController {

    @Autowired
    DesignacaoService designacaoService;

    @GetMapping(value = "/{id}", name = "", produces = "application/json")
    public DesignacaoDto getDesignacao(@PathVariable Long id) {
        return designacaoService.find(id);
    }

    @GetMapping(value = "list", name = "", produces = "application/json")
    public List<DesignacaoDto> getDesignacaoList() {
        return designacaoService.list();
    }

    @PostMapping(value = "", name = "", produces = "application/json")
    public DesignacaoDto save(@RequestBody DesignacaoDto designacaoDto) {
        return designacaoService.save(designacaoDto);
    }

    @PostMapping(value = "save-all", name = "", produces = "application/json")
    public List<DesignacaoDto> saveAll(@RequestBody List<DesignacaoDto> designacaoDtoList) {
        return designacaoService.saveAll(designacaoDtoList);
    }

    @DeleteMapping(value = "/{id}", name = "", produces = "application/json")
    public void delete(@PathVariable Long id) {
        designacaoService.delete(id);
    }

    @GetMapping(value = "/inicio/{inicio}/fim/{fim}", name = "", produces = "application/json")
    public List<DesignacaoDto> findByDate(@PathVariable String inicio, @PathVariable String fim) {
        return designacaoService.findByDate(inicio, fim);
    }

    @GetMapping(value = "/mes/{mes}", name = "", produces = "application/json")
    public List<DesignacaoDto> findByMes(@PathVariable int mes) {
        return designacaoService.findByMes(mes);
    }

}
