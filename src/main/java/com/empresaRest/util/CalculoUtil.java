package com.empresaRest.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.empresaRest.model.Colaborador;
import com.empresaRest.repository.ColaboradorRepository;

@Service
public class CalculoUtil {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public boolean calcularIdade(Integer idade) {
		double maximoPermitido = (colaboradorRepository.totalColaboradores()*20)/100;
		if (maximoPermitido < idade) {
			return true;
		}
		return false;
	}

	public ResponseEntity<?> isMaiorSessentaCinco() {
		List<Colaborador> colaboradores = colaboradorRepository.findAll();
		long cont = 1;		
		for (Colaborador col : colaboradores) {
			if(col.getIdade() > 65) {
				cont++;
			}
		}
		double maximoPermitido = (colaboradorRepository.totalColaboradores()*20)/100;
		if ( cont > maximoPermitido) {
			return new ResponseEntity<>("Máxinmo de colaboradores com 65 anos", HttpStatus.OK);
		}
		return null;
	}

}
