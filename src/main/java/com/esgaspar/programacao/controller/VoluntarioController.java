package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.esgaspar.programacao.service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

    @Autowired
    VoluntarioService voluntarioService;

    @GetMapping(value = "/{id}", name = "", produces = "application/json")
    public VoluntarioDto getVoluntario(@PathVariable Long id) {
        return voluntarioService.find(id);
    }

    @GetMapping(value = "list", name = "", produces = "application/json")
    public List<VoluntarioDto> getVoluntarioList() {
        return voluntarioService.list();
    }

    @GetMapping(value = "list/nome/{nome}", name = "", produces = "application/json")
    public List<VoluntarioDto> getVoluntarioList(@PathVariable String nome) {
        List<VoluntarioDto> result = voluntarioService.listByNome(nome);
        return result;
    }


    @PostMapping(value = "", name = "", produces = "application/json")
    public VoluntarioDto save(@RequestBody VoluntarioDto voluntarioDto) {
        return voluntarioService.save(voluntarioDto);
    }

    @DeleteMapping(value = "/{id}", name = "", produces = "application/json")
    public void delete(@PathVariable Long id) {
        voluntarioService.delete(id);
    }

}
