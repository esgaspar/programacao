package com.esgaspar.programacao.repository;

import com.esgaspar.programacao.model.Designacao;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DesignacaoRepository extends JpaRepository<Designacao, Long> {

    @Query(value = "from Designacao d where data BETWEEN :inicio AND :fim Order By data, d.privilegio.ordem")
    List<Designacao> findByData(LocalDate inicio, LocalDate fim);

    @Query("select d from Designacao d where month(d.data) = :mes Order By data, d.privilegio.ordem")
    List<Designacao> findByMes(int mes);
}