package com.fernandoalencar.gerenciador_de_sinuca.domain.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoalencar.gerenciador_de_sinuca.domain.exception.NegocioException;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Movimentacao;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.StatusSinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.ClienteRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.MovimentacaoRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.SinucaRepository;

@Service
public class SinucaService {

	@Autowired
	private SinucaRepository sinucaRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	public Sinuca criar(Sinuca sinuca) {
		Cliente cliente = clienteRepository.findById(sinuca.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));

		sinuca.setCliente(cliente);
		sinuca.setStatus(StatusSinuca.DISPONIVEL);
		sinuca.setDataAbertura(OffsetDateTime.now());

		return sinucaRepository.save(sinuca);
	}

	public void excluir(Long sinucaId) {
		sinucaRepository.deleteById(sinucaId);
	}

	public Sinuca atualizar(Sinuca sinuca) {
		
		Cliente cliente = clienteRepository.findById(sinuca.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		return sinucaRepository.save(sinuca);
	}
	
	public Movimentacao adicionarMovimentacao(Long sinucaId, Integer fichas, Integer descontoFichas, Integer totalFichasCliente) {
		Sinuca sinuca = sinucaRepository.findById(sinucaId)
				.orElseThrow(() -> new NegocioException("Sinuca não encontrado"));
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setSinuca(sinuca);
		movimentacao.setDataMovimentacao(OffsetDateTime.now());
		movimentacao.setFichas(fichas);
		movimentacao.setDescontoFichas(descontoFichas);
		movimentacao.setTotalFichasCliente(totalFichasCliente);
		
		if (movimentacao.getTotalFinalFichas() == null ) {
			movimentacao.setTotalFinalFichas(0);
		}
		
		calcular(movimentacao);
		return movimentacaoRepository.save(movimentacao);
		
	}
	
	private void calcular(Movimentacao movimentacao) {
		
		Integer contador = movimentacao.getSinuca().getContadorFicha() + movimentacao.getFichas();
		Integer gerarDescontoFichas = movimentacao.getFichas() - movimentacao.getDescontoFichas();
		
		Double totalEmpresa = ((gerarDescontoFichas * movimentacao.getSinuca().getPorcentagemEmpresa()) / 100);
		Double fichasPendentes = (((gerarDescontoFichas * movimentacao.getSinuca().getPorcentagemCliente()) / 100) - movimentacao.getTotalFichasCliente());
		
		movimentacao.getSinuca().setContadorFicha(contador);
		movimentacao.setTotalFichasEmpresa(totalEmpresa.intValue());
		movimentacao.setDiferencaFichas(fichasPendentes.intValue());
		
		Integer totalFinal = movimentacao.getTotalFinalFichas() + fichasPendentes.intValue();
		
		movimentacao.setTotalFinalFichas(totalFinal);
	}
}
