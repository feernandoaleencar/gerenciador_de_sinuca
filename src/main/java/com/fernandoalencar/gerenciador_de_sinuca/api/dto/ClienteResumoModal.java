package com.fernandoalencar.gerenciador_de_sinuca.api.dto;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Endereco;

public class ClienteResumoModal {

	private Long id;
	private String nome;
	private String cpf;
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
