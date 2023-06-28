package com.esgaspar.programacao.repository;

import com.esgaspar.programacao.model.Privilegio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivilegioRepository extends JpaRepository<Privilegio, Long> {

    List<Privilegio> findAllByOrderByOrdem();

}