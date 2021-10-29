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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoalencar.gerenciador_de_sinuca.api.model.SinucaInputModel;
import com.fernandoalencar.gerenciador_de_sinuca.api.model.SinucaModel;
import com.fernandoalencar.gerenciador_de_sinuca.domain.exception.NegocioException;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.StatusSinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.SinucaRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.filter.SinucaFilter;
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
	public List<SinucaModel> pesquisar(SinucaFilter sinucaFilter) {
		return toCollectionModel(sinucaRepository.filtrar(sinucaFilter));
	}

	@GetMapping("/{sinucaId}")
	public ResponseEntity<SinucaModel> buscar(@PathVariable Long sinucaId) {
		Optional<Sinuca> sinuca = sinucaRepository.findById(sinucaId);

		if (sinuca.isPresent()) {
			SinucaModel sinucamodel = toModel(sinuca.get());
			return ResponseEntity.ok(sinucamodel);
		}

		return ResponseEntity.notFound().build();
	}

	// Método excluir cliente
	@DeleteMapping("/{sinucaId}")
	public ResponseEntity<Void> remover(@PathVariable Long sinucaId) {

		// verificar se o cliente existe
		if (!sinucaRepository.existsById(sinucaId)) {
			return ResponseEntity.notFound().build();
		}

		// verificar o status e datafechamento
		if (sinucaRepository.getById(sinucaId).getStatus().equals(StatusSinuca.ENCERRADA)
				&& (sinucaRepository.getById(sinucaId).getDataFechamento() != null)) {
			sinucaService.excluir(sinucaId);
			return ResponseEntity.noContent().build();
		} else if (!(sinucaRepository.getById(sinucaId).getStatus().equals(StatusSinuca.ENCERRADA))
				&& (sinucaRepository.getById(sinucaId).getDataFechamento() != null)) {
			throw new NegocioException("Verifique o status da sinuca.");
		} else if (sinucaRepository.getById(sinucaId).getStatus().equals(StatusSinuca.ENCERRADA)
				&& (sinucaRepository.getById(sinucaId).getDataFechamento() == null)) {
			throw new NegocioException("Informe a data de fechamento da sinuca.");
		} else {
			throw new NegocioException("Informe a data de fechamento e o status da sinuca.");
		}

	}

	// Método atualizar sinuca
	@PutMapping("/{sinucaId}")
	public ResponseEntity<Sinuca> atualizar(@Valid @PathVariable Long sinucaId, @RequestBody Sinuca sinuca) {

		if (!sinucaRepository.existsById(sinucaId)) {
			return ResponseEntity.notFound().build();
		}

		sinuca.setId(sinucaId);
		sinuca = sinucaService.atualizar(sinuca);

		return ResponseEntity.ok(sinuca);
	}
	
	@PutMapping("/{sinucaId}/ficarDisponivel")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ficarDisponivel(@PathVariable Long sinucaId) {
		sinucaService.ficarDisponivel(sinucaId);
	}
	
	@PutMapping("/{sinucaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void encerrarSinuca(@PathVariable Long sinucaId) {
		sinucaService.encerrarSinuca(sinucaId);
	}
	
	@PutMapping("/{sinucaId}/alugar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alugarSinuca(@PathVariable Long sinucaId) {
		sinucaService.alugarSinuca(sinucaId);
	}
	
	@PutMapping("/{sinucaId}/fazerManutencao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fazerManutencaoSinuca(@PathVariable Long sinucaId) {
		sinucaService.fazerManutencaoSinuca(sinucaId);
	}
	
	@PutMapping("/{sinucaId}/quebrar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void quebrarSinuca(@PathVariable Long sinucaId) {
		sinucaService.quebrarSinuca(sinucaId);
	}

	private SinucaModel toModel(Sinuca sinuca) {
		return modelMapper.map(sinuca, SinucaModel.class);
	}

	private List<SinucaModel> toCollectionModel(List<Sinuca> sinucas) {
		return sinucas.stream().map(sinuca -> toModel(sinuca)).collect(Collectors.toList());
	}

	private Sinuca toEntity(SinucaInputModel sinucaInputModel) {
		return modelMapper.map(sinucaInputModel, Sinuca.class);
	}

}
