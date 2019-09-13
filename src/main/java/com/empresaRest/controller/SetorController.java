package com.empresaRest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaRest.dto.ColaboradorDTO;

import com.empresaRest.model.Setor;

import com.empresaRest.service.SetorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
public class SetorController {

	@Autowired
	private SetorService service;

	@GetMapping("/setores/todos")
	@ApiOperation(value = "Busca todos os setores")
	public ResponseEntity<List<Setor>> buscaTodos() {
		List<Setor> setores = service.findAll();
		return ResponseEntity.ok().body(setores);
	}

	@GetMapping("/setores/todos/{id}")
	@ApiOperation(value = "Busca um setor por ID")
	public ResponseEntity<?> buscaSetor(@PathVariable Integer id) {
		Optional<Setor> setor = service.findBySetor(id);
		return ResponseEntity.ok().body(setor);
	}

	@GetMapping("/setores/{id}")
	@ApiOperation(value = "Busca um setor DTO por ID")
	public ResponseEntity<List<ColaboradorDTO>> buscaSetoresPorId(@PathVariable Integer id) {
		List<ColaboradorDTO> colaboradoresDto = service.findSetorById(id);
		return ResponseEntity.ok().body(colaboradoresDto);
	}

}
