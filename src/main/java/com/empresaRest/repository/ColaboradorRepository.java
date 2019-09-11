package com.empresaRest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresaRest.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer>{
	
	public Colaborador findByCpf(String cpf);
	public Optional<Colaborador> findById(Integer id);
}
