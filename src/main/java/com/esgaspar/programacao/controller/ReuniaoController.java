package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.service.ReuniaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/escola")
@RequiredArgsConstructor
public class ReuniaoController {

    private final ReuniaoService service;

    @GetMapping
    public ResponseEntity<Void> crawl() {
        service.crawl();
        return ResponseEntity.ok().build();
    }
}
