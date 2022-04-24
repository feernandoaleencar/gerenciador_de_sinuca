package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.ClienteRepository;
import com.fernandoalencar.gerenciador_de_sinuca.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	//Atributos da classe
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	//Método listar clientes
	@GetMapping
	public Page<Cliente> listar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable){
		return clienteRepository.findByNomeContaining(nome, pageable);
	}
	
	
	//Método listar clientes pesquisando por ID 
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	//Método adicionar cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}
	
	
	//Método atualizar cliente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build(); 
		}
		
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	
	//Método excluir cliente 
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
}
