package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.service.PrivilegioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/privilegio")
@RequiredArgsConstructor
public class PrivilegioController {

    private final PrivilegioService service;

    @GetMapping("/{id}")
    public ResponseEntity<PrivilegioDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PrivilegioDto>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<PrivilegioDto> save(@RequestBody PrivilegioDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<PrivilegioDto>> saveAll(@RequestBody List<PrivilegioDto> dtos) {
        return ResponseEntity.ok(service.saveAll(dtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
