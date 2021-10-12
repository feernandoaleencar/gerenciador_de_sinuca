package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoalencar.gerenciador_de_sinuca.api.model.MovimentacaoInputModel;
import com.fernandoalencar.gerenciador_de_sinuca.api.model.MovimentacaoModel;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Movimentacao;
import com.fernandoalencar.gerenciador_de_sinuca.domain.service.SinucaService;

@RestController
@RequestMapping("/sinucas/{sinucaId}/movimentacao")
public class MovimentacaoController {
	
	@Autowired
	private SinucaService sinucaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
