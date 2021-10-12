package com.fernandoalencar.gerenciador_de_sinuca.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Movimentacao {

	// Atributos da classe movimentação
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Sinuca sinuca;
	
	private OffsetDateTime dataMovimentacao;
	private Integer fichas;
	private Integer descontoFichas;
	private Double totalFichasEmpresa;
	private Double totalFichasCliente;
	private Double diferencaFichas;
	private Double totalFinalFichas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sinuca getSinuca() {
		return sinuca;
	}

	public void setSinuca(Sinuca sinuca) {
		this.sinuca = sinuca;
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

	public Double getTotalFichasEmpresa() {
		return totalFichasEmpresa;
	}

	public void setTotalFichasEmpresa(Double totalFichasEmpresa) {
		this.totalFichasEmpresa = totalFichasEmpresa;
	}

	public Double getTotalFichasCliente() {
		return totalFichasCliente;
	}

	public void setTotalFichasCliente(Double totalFichasCliente) {
		this.totalFichasCliente = totalFichasCliente;
	}

	public Double getDiferencaFichas() {
		return diferencaFichas;
	}

	public void setDiferencaFichas(Double diferencaFichas) {
		this.diferencaFichas = diferencaFichas;
	}

	public Double getTotalFinalFichas() {
		return totalFinalFichas;
	}

	public void setTotalFinalFichas(Double totalFinalFichas) {
		this.totalFinalFichas = totalFinalFichas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
