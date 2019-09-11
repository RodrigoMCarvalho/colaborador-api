package com.empresaRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresaRest.model.Colaborador;
import com.empresaRest.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository repository;
	
	public Colaborador save(Colaborador colaborador) {
		return repository.save(colaborador);
	}
}
