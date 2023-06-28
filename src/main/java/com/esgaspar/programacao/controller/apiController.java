package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.service.PrivilegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class apiController {

    @Autowired
    PrivilegioService privilegioService;

    @GetMapping(value = "/{id}", name = "", produces = "application/json")
    public PrivilegioDto getPrivilegio(@PathVariable Long id) {
        return privilegioService.find(id);
    }

}