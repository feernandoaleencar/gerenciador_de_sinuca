package com.fernandoalencar.gerenciador_de_sinuca.domain.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoalencar.gerenciador_de_sinuca.domain.exception.NegocioException;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.StatusSinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.ClienteRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.SinucaRepository;

@Service
public class SinucaService {

	@Autowired
	private SinucaRepository sinucaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

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
}
