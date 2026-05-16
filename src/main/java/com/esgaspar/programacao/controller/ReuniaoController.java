package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.service.ReuniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/escola")
public class ReuniaoController {

    @Autowired
    ReuniaoService service;

    @GetMapping( name = "", produces = "application/json")
    public UserDto get() {
        return service.find();
    }
}
