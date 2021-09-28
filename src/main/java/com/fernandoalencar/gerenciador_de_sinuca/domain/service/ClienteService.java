package com.fernandoalencar.gerenciador_de_sinuca.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandoalencar.gerenciador_de_sinuca.domain.exception.NegocioException;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.ClienteRepository;

@Service
public class ClienteService {
	
	//Atributos da classe
	@Autowired
	private ClienteRepository clienteRepository;
	
	//Método metodo para salvar cliente com validação de email e cliente existente
	public Cliente salvar(Cliente cliente) {
		
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Email já cadastrado em outro cliente.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	//Método para excluir o cliente
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
