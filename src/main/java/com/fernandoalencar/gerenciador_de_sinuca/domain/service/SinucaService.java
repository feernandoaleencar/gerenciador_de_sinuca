package com.fernandoalencar.gerenciador_de_sinuca.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.StatusSinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.SinucaRepository;

@Service
public class SinucaService {

	@Autowired
	private SinucaRepository sinucaRepository;
	
	public Sinuca criar(Sinuca sinuca) {
		
		sinuca.setStatus(StatusSinuca.DISPONIVEL);
		sinuca.setDataAbertura(LocalDateTime.now());
		
		return sinucaRepository.save(sinuca);
	}
}
