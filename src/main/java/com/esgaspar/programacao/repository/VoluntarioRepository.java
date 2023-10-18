package com.esgaspar.programacao.repository;

import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    List<Voluntario> findAllByOrderByNome();

    @Query("Select v from Voluntario v where upper(v.nome) like %:nome% ORDER BY v.nome")
    List<Voluntario> findByNomeContainingIgnoreCase(String nome);

}