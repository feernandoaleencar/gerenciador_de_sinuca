package com.fernandoalencar.gerenciador_de_sinuca.api.dto;

import javax.validation.constraints.NotNull;

public class MovimentacaoInputModel {

	@NotNull
	private Integer fichas;

	@NotNull
	private Integer descontoFichas;

	@NotNull
	private Integer totalFichasCliente;

	@NotNull
	public Integer getFichas() {
		return fichas;
	}

	public void setFichas(Integer fichas) {
		this.fichas = fichas;
	}

	public Integer getDescontoFichas() {
		return descontoFichas;
	}

	public void setDescontoFichas(Integer descontoFichas) {
		this.descontoFichas = descontoFichas;
	}

	public Integer getTotalFichasCliente() {
		return totalFichasCliente;
	}

	public void setTotalFichasCliente(Integer totalFichasCliente) {
		this.totalFichasCliente = totalFichasCliente;
	}

}
