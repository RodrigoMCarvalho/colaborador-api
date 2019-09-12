package com.empresaRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empresaRest.model.Colaborador;
import com.empresaRest.model.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {
	
	@Query("SELECT c FROM Colaborador c JOIN Setor s ON c.setor = s.id WHERE s.id =:id")
	List<Colaborador> findColaboradoresById(@Param(value = "id") Integer id);
	
	
	
}
