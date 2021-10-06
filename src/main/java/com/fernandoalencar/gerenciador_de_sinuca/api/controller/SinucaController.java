package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoalencar.gerenciador_de_sinuca.api.model.SinucaInputModel;
import com.fernandoalencar.gerenciador_de_sinuca.api.model.SinucaModel;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.StatusSinuca;
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
	public SinucaModel criar(@Valid @RequestBody SinucaInputModel sinucaInputModel) {
		Sinuca sinuca = toEntity(sinucaInputModel);
		return toModel(sinucaService.criar(sinuca));
	}
	
	@GetMapping
	public List<SinucaModel> listar(){
		return toCollectionModel(sinucaRepository.findAll()); 
	}
	
	@GetMapping("/{sinucaId}")
	public ResponseEntity<SinucaModel> buscar(@PathVariable Long sinucaId){
		 Optional<Sinuca> sinuca = sinucaRepository.findById(sinucaId); 
		 
		 if (sinuca.isPresent()) {
			 SinucaModel sinucamodel = toModel(sinuca.get());
			 return ResponseEntity.ok(sinucamodel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	private SinucaModel toModel(Sinuca sinuca) {
		return 	modelMapper.map(sinuca, SinucaModel.class);	
	}
	
	private List<SinucaModel> toCollectionModel(List<Sinuca> sinucas){
		return sinucas.stream().map(sinuca -> toModel(sinuca)).collect(Collectors.toList());
	}
	
	private Sinuca toEntity(SinucaInputModel sinucaInputModel) {
		return modelMapper.map(sinucaInputModel, Sinuca.class);
	}
	
	//Método excluir cliente 
		@DeleteMapping("/{sinucaId}")
		public ResponseEntity<Void> remover(@PathVariable Long sinucaId){
			if (!sinucaRepository.existsById(sinucaId) && sinucaRepository.equals(StatusSinuca.ENCERRADA) ) {
				return ResponseEntity.notFound().build();
			}
			
			sinucaService.excluir(sinucaId);
			
			return ResponseEntity.noContent().build();
		}
}
