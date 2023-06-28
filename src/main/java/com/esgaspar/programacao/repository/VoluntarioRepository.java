package com.esgaspar.programacao.repository;

import com.esgaspar.programacao.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {}