package com.jdo.poc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jdo.poc.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer>{

	List<Consulta> findByDataHoraBetween(Date dataInicioDia, Date dataFimDia);

}