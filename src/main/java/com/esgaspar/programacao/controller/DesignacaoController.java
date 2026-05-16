package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.DesignacaoDto;
import com.esgaspar.programacao.service.DesignacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designacao")
@RequiredArgsConstructor
public class DesignacaoController {

    private final DesignacaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<DesignacaoDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DesignacaoDto>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<DesignacaoDto> save(@RequestBody DesignacaoDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<DesignacaoDto>> saveAll(@RequestBody List<DesignacaoDto> dtos) {
        return ResponseEntity.ok(service.saveAll(dtos));
    }

    @PostMapping("/delete-all")
    public ResponseEntity<Void> deleteAll(@RequestBody List<DesignacaoDto> dtos) {
        service.deleteAll(dtos);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inicio/{inicio}/fim/{fim}")
    public ResponseEntity<List<DesignacaoDto>> findByDate(@PathVariable String inicio,
                                                          @PathVariable String fim) {
        return ResponseEntity.ok(service.findByDate(inicio, fim));
    }

    @GetMapping("/mes/{mes}")
    public ResponseEntity<List<DesignacaoDto>> findByMes(@PathVariable int mes) {
        return ResponseEntity.ok(service.findByMes(mes));
    }
}
