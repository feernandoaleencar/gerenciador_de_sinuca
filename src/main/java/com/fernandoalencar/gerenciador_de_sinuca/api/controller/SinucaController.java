package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.service.SinucaService;

@RestController
@RequestMapping("/sinuca")
public class SinucaController {
	
	@Autowired
	private SinucaService sinucaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Sinuca criar(@Valid @RequestBody Sinuca sinuca) {
		return sinucaService.criar(sinuca);
	}
	
}
