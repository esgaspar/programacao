package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.esgaspar.programacao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(value = "/{id}", name = "", produces = "application/json")
    public UserDto get(@PathVariable Long id) {
        return service.find(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "list", name = "", produces = "application/json")
    public List<UserDto> list() {
        return service.list();
    }

    @PostMapping(value = "", name = "", produces = "application/json")
    public UserDto save(@RequestBody UserDto dto) {
        return service.save(dto);
    }

    @DeleteMapping(value = "/{id}", name = "", produces = "application/json")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
