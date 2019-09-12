package com.empresaRest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaRest.dto.ColaboradorDTO;
import com.empresaRest.exception.HttpMessageNotReadableException;
import com.empresaRest.exception.ResourceNotFoundException;
import com.empresaRest.model.Colaborador;
import com.empresaRest.model.Setor;
import com.empresaRest.repository.ColaboradorRepository;
import com.empresaRest.repository.SetorRepository;
import com.empresaRest.util.CalculoUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorRepository repository;

	@Autowired
	private CalculoUtil util;

	@PostMapping
	@ApiOperation(value = "Salva um novo colaborador")
	public ResponseEntity<?> salvar(@Valid @RequestBody Colaborador colaborador) {
		if (colaborador == null) {
			throw new HttpMessageNotReadableException("Favor informar os dados do colaborador");
		}
		repository.save(colaborador);
		return new ResponseEntity<>("Colaborador salvo com sucesso!", HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Colaborador>> buscaTodos() {
		List<Colaborador> colaboradores = repository.findAll();
		return ResponseEntity.ok().body(colaboradores);
	}

	@GetMapping("/todos")
	@ApiOperation(value = "Retorna todos os colaboradores")
	public ResponseEntity<List<ColaboradorDTO>> buscaTodosDTO() {
		List<Colaborador> colaboradores = repository.findAll();
		List<ColaboradorDTO> colaboradoresDto = colaboradores.stream().map(col -> new ColaboradorDTO(col))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(colaboradoresDto);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um colaborador por ID")
	public ResponseEntity<Optional<Colaborador>> buscaColaboradorPorId(@PathVariable Integer id) {
		verificaSeColaboradorExiste(id);
		Optional<Colaborador> colaborador = repository.findById(id);
		return ResponseEntity.ok().body(colaborador);
	}

	@PutMapping
	@ApiOperation(value = "Atualiza um colaborador")
	public ResponseEntity<?> atualizar(@RequestBody Colaborador colaborador) {
		verificaSeColaboradorExiste(colaborador.getId());
		repository.save(colaborador);
		return new ResponseEntity<>("Colaborador atualizado com sucesso!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um novo colaborador")
	public ResponseEntity<?> remover(@PathVariable Integer id) {
		verificaSeColaboradorExiste(id);
		repository.deleteById(id);
		return new ResponseEntity<>("Colaborador removido com sucesso!", HttpStatus.OK);
	}

	private void verificaSeColaboradorExiste(Integer id) {
		Optional<Colaborador> colaborador = repository.findById(id);
		if (!colaborador.isPresent()) {
			throw new ResourceNotFoundException("Não foi encontrado um colaborador para o ID: " + id);
		}
	}

}
