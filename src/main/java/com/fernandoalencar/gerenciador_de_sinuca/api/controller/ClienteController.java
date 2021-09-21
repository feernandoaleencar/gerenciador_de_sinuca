package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Fernando Alencar");
		cliente1.setCpf("123.456.789-01");
		cliente1.setEmail("seuemail@email.com");
		cliente1.setTelefone("61 983846739");
		cliente1.setEndereco("Minha casa");
		cliente1.setCidade("Brasília");
				
		return Arrays.asList(cliente1);
	}
}
