package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import com.fernandoalencar.gerenciador_de_sinuca.api.dto.MovimentacaoInputModel;
import com.fernandoalencar.gerenciador_de_sinuca.api.dto.MovimentacaoModel;
import com.fernandoalencar.gerenciador_de_sinuca.domain.exception.EntidadeNaoEncontradaException;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Movimentacao;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.MovimentacaoRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.SinucaRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.service.SinucaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sinucas/{sinucaId}/movimentacao")
public class MovimentacaoController {

	@Autowired
	private SinucaRepository sinucaRepository;

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Autowired
	private SinucaService sinucaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public Page<Movimentacao> listar(@PathVariable Long sinucaId, Pageable pageable){

		Sinuca sinuca = sinucaRepository.findById(sinucaId).orElseThrow(() -> new EntidadeNaoEncontradaException("Sinuca não encontrada"));

		return movimentacaoRepository.findBySinuca(sinuca, pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MovimentacaoModel adicionar(@PathVariable Long sinucaId, @Valid @RequestBody MovimentacaoInputModel movimentaInputModel) {
		Movimentacao movimentacao = sinucaService.adicionarMovimentacao(sinucaId, movimentaInputModel.getFichas(), movimentaInputModel.getDescontoFichas(), movimentaInputModel.getTotalFichasCliente());
		
		return toModel(movimentacao);
	}
	
	private MovimentacaoModel toModel(Movimentacao movimentacao) {
		return modelMapper.map(movimentacao, MovimentacaoModel.class);
	}

}
