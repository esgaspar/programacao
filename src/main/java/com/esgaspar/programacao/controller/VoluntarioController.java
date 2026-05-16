package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.esgaspar.programacao.service.VoluntarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voluntario")
@RequiredArgsConstructor
public class VoluntarioController {

    private final VoluntarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<VoluntarioDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<VoluntarioDto>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/list/nome/{nome}")
    public ResponseEntity<List<VoluntarioDto>> listByNome(@PathVariable String nome) {
        return ResponseEntity.ok(service.listByNome(nome));
    }

    @PostMapping
    public ResponseEntity<VoluntarioDto> save(@RequestBody VoluntarioDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
