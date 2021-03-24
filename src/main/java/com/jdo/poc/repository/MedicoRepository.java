package com.jdo.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdo.poc.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer>{

	Medico findByNome(String nome);
}
