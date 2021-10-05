package com.fernandoalencar.gerenciador_de_sinuca.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SinucaInputModel {
	
	@Valid
	@NotNull
	private ClienteIdInputModel cliente;

	@NotNull
	private Integer contadorFicha;
	
	@NotNull
	private BigDecimal vlrFicha;
	
	@NotNull
	private Double porcentagemEmpresa;
	
	@NotNull
	private Double porcentagemCliente;
	
	@NotNull
	private Integer patrimonio;

	public ClienteIdInputModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdInputModel cliente) {
		this.cliente = cliente;
	}

	public Integer getContadorFicha() {
		return contadorFicha;
	}

	public void setContadorFicha(Integer contadorFicha) {
		this.contadorFicha = contadorFicha;
	}

	public BigDecimal getVlrFicha() {
		return vlrFicha;
	}

	public void setVlrFicha(BigDecimal vlrFicha) {
		this.vlrFicha = vlrFicha;
	}

	public Double getPorcentagemEmpresa() {
		return porcentagemEmpresa;
	}

	public void setPorcentagemEmpresa(Double porcentagemEmpresa) {
		this.porcentagemEmpresa = porcentagemEmpresa;
	}

	public Double getPorcentagemCliente() {
		return porcentagemCliente;
	}

	public void setPorcentagemCliente(Double porcentagemCliente) {
		this.porcentagemCliente = porcentagemCliente;
	}

	public Integer getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Integer patrimonio) {
		this.patrimonio = patrimonio;
	}

}
