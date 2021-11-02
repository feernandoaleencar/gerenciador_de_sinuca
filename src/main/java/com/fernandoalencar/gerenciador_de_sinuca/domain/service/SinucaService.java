package com.fernandoalencar.gerenciador_de_sinuca.domain.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoalencar.gerenciador_de_sinuca.domain.exception.EntidadeNaoEncontradaException;
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
		sinuca.setDataAbertura(LocalDate.now());
		sinuca.setFichasDevedor(0);
		sinuca.setvlrTotalFichasDevedor(0.0);

		return sinucaRepository.save(sinuca);
	}

	public void excluir(Long sinucaId) {
		sinucaRepository.deleteById(sinucaId);
	}

	public Sinuca atualizar(Sinuca sinuca) {
		
		Cliente cliente = clienteRepository.findById(sinuca.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		if (sinuca.getFichasDevedor() == null) {
			sinuca.setFichasDevedor(0);
		}
		
		return sinucaRepository.save(sinuca);
	}
	
	public Movimentacao adicionarMovimentacao(Long sinucaId, Integer fichas, Integer descontoFichas, Integer totalFichasCliente) {
		Sinuca sinuca = verificarSinuca(sinucaId);
		
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
	
	public void ficarDisponivel(Long sinucaId) {
		Sinuca sinuca = verificarSinuca(sinucaId);
		
		sinuca.ficarDisponivel();
		
		sinucaRepository.save(sinuca);
	}
	
	public void encerrarSinuca(Long sinucaId){
		Sinuca sinuca = verificarSinuca(sinucaId);
		
		sinuca.finalizar();
		
		sinucaRepository.save(sinuca);
	}
	
	public void alugarSinuca(Long sinucaId) {
		Sinuca sinuca = verificarSinuca(sinucaId);
		
		sinuca.alugar();
		
		sinucaRepository.save(sinuca);
	}
	
	public void fazerManutencaoSinuca(Long sinucaId) {
		
		Sinuca sinuca = verificarSinuca(sinucaId);
		
		sinuca.fazerManutencao();
		
		sinucaRepository.save(sinuca);
	}
	
	public void quebrarSinuca(Long sinucaId) {
		Sinuca sinuca = verificarSinuca(sinucaId);
		
		sinuca.quebrarSinuca();
		
		sinucaRepository.save(sinuca);
	}

	private Sinuca verificarSinuca(Long sinucaId) {
		return sinucaRepository.findById(sinucaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Sinuca não encontrado"));
	}
	
	private void calcular(Movimentacao movimentacao) {
		
		Integer contador = movimentacao.getSinuca().getContadorFicha() + movimentacao.getFichas();
		Integer gerarDescontoFichas = movimentacao.getFichas() - movimentacao.getDescontoFichas();
		
		Double totalEmpresa = ((gerarDescontoFichas * movimentacao.getSinuca().getPorcentagemEmpresa()) / 100);
		Double fichasPendentes = (((gerarDescontoFichas * movimentacao.getSinuca().getPorcentagemCliente()) / 100) - movimentacao.getTotalFichasCliente());
		
		if (movimentacao.getTotalFinalFichas() == null ) {
			movimentacao.setTotalFinalFichas(0);
		}
		
		movimentacao.getSinuca().setContadorFicha(contador);
		movimentacao.setTotalFichasEmpresa(totalEmpresa.intValue());
		movimentacao.setDiferencaFichas(fichasPendentes.intValue());
		
		movimentacao.setVlrTotalFichasDesconto(movimentacao.getDescontoFichas() * movimentacao.getSinuca().getVlrFicha());
		movimentacao.setVlrTotalFichas(movimentacao.getFichas() * movimentacao.getSinuca().getVlrFicha());
		movimentacao.setVlrTotalFichasEmpresa(movimentacao.getTotalFichasEmpresa() * movimentacao.getSinuca().getVlrFicha());
		movimentacao.setVlrTotalFichasCliente(movimentacao.getTotalFichasCliente() * movimentacao.getSinuca().getVlrFicha());
		
		Integer totalFinal = movimentacao.getSinuca().getFichasDevedor() + fichasPendentes.intValue();
		Integer totalFinalFichas = totalFinal + movimentacao.getTotalFinalFichas();
		
		movimentacao.getSinuca().setFichasDevedor(totalFinal);
		movimentacao.setTotalFinalFichas(totalFinalFichas);
		movimentacao.getSinuca().setvlrTotalFichasDevedor(movimentacao.getSinuca().getFichasDevedor() * movimentacao.getSinuca().getVlrFicha());
	}
	
}
