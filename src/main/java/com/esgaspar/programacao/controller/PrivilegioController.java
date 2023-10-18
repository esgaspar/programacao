package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.service.PrivilegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/privilegio")
public class PrivilegioController {

    @Autowired
    PrivilegioService privilegioService;

    @GetMapping(value = "/{id}", name = "", produces = "application/json")
    public PrivilegioDto getPrivilegio(@PathVariable Long id) {
        return privilegioService.find(id);
    }

    @GetMapping(value = "list", name = "", produces = "application/json")
    public List<PrivilegioDto> getPrivilegioList() {
        return privilegioService.list();
    }

    @PostMapping(value = "", name = "", produces = "application/json")
    public PrivilegioDto save(@RequestBody PrivilegioDto privilegioDto) {
        return privilegioService.save(privilegioDto);
    }

    //@PostMapping(value = "save-all", name = "", produces = "application/json")

    @RequestMapping(value = "/save-all",
            produces = "application/json",
            method=RequestMethod.POST)
    public List<PrivilegioDto> saveAll(@RequestBody List<PrivilegioDto> privilegioDtoList) {
        return privilegioService.saveAll(privilegioDtoList);
    }

    @DeleteMapping(value = "/{id}", name = "", produces = "application/json")
    public void delete(@PathVariable Long id) {
        privilegioService.delete(id);
    }

}
