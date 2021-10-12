package com.fernandoalencar.gerenciador_de_sinuca.api.model;

import java.time.OffsetDateTime;

public class MovimentacaoModel {

	private Long id;
	private OffsetDateTime dataMovimentacao;
	private Integer fichas;
	private Integer descontoFichas;
	private Integer totalFichasEmpresa;
	private Integer totalFichasCliente;
	private Integer diferencaFichas;
	private Integer totalFinalFichas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(OffsetDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

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

	public Integer getTotalFichasEmpresa() {
		return totalFichasEmpresa;
	}

	public void setTotalFichasEmpresa(Integer totalFichasEmpresa) {
		this.totalFichasEmpresa = totalFichasEmpresa;
	}

	public Integer getTotalFichasCliente() {
		return totalFichasCliente;
	}

	public void setTotalFichasCliente(Integer totalFichasCliente) {
		this.totalFichasCliente = totalFichasCliente;
	}

	public Integer getDiferencaFichas() {
		return diferencaFichas;
	}

	public void setDiferencaFichas(Integer diferencaFichas) {
		this.diferencaFichas = diferencaFichas;
	}

	public Integer getTotalFinalFichas() {
		return totalFinalFichas;
	}

	public void setTotalFinalFichas(Integer totalFinalFichas) {
		this.totalFinalFichas = totalFinalFichas;
	}

}
