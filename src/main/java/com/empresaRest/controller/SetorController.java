package com.empresaRest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaRest.dto.ColaboradorDTO;
import com.empresaRest.model.Colaborador;
import com.empresaRest.model.Setor;
import com.empresaRest.repository.SetorRepository;

@RestController
@RequestMapping("/setores")
public class SetorController {

	@Autowired
	private SetorRepository repository;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Setor>> buscaTodos() {
		List<Setor> setores = repository.findAll();
		return ResponseEntity.ok().body(setores);
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> buscaSetor(@PathVariable Integer id) {
		Optional<Setor> setor = repository.findById(id);
		return ResponseEntity.ok().body(setor);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ColaboradorDTO>> buscaSetoresPorId(@PathVariable Integer id ) {
		List<Colaborador> colaboradores = repository.findColaboradoresById(id);
		List<ColaboradorDTO> colaboradoresDto = colaboradores.stream().map(col -> new ColaboradorDTO(col))
				.collect(Collectors.toList());
				
		return ResponseEntity.ok().body(colaboradoresDto);
	}
	
	
}
