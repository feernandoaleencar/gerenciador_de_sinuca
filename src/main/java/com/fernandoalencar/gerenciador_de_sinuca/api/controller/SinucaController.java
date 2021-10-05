package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoalencar.gerenciador_de_sinuca.api.model.SinucaModel;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.SinucaRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.service.SinucaService;

@RestController
@RequestMapping("/sinucas")
public class SinucaController {
	
	@Autowired
	private SinucaService sinucaService;
	
	@Autowired
	private SinucaRepository sinucaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Sinuca criar(@Valid @RequestBody Sinuca sinuca) {
		return sinucaService.criar(sinuca);
	}
	
	@GetMapping
	public List<Sinuca> listar(){
		return sinucaRepository.findAll(); 
	}
	
	@GetMapping("/{sinucaId}")
	public ResponseEntity<SinucaModel> buscar(@PathVariable Long sinucaId){
		 Optional<Sinuca> sinuca = sinucaRepository.findById(sinucaId); 
		 
		 if (sinuca.isPresent()) {
			 SinucaModel sinucamodel = modelMapper.map(sinuca.get(), SinucaModel.class);
			 return ResponseEntity.ok(sinucamodel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
}
