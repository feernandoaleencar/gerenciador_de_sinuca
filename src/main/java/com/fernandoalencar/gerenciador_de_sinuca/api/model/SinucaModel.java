package com.fernandoalencar.gerenciador_de_sinuca.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.StatusSinuca;

public class SinucaModel {

	private Long id;
	private ClienteResumoModal cliente;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFechamento;
	private StatusSinuca status;
	private Integer contadorFicha;
	private BigDecimal vlrFicha;
	private Double porcentagemEmpresa;
	private Double porcentagemCliente;
	private Integer patrimonio;
	private Integer fichasDevedor;
	private Double vlrTotalFichasDevedor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public OffsetDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(OffsetDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public StatusSinuca getStatus() {
		return status;
	}

	public void setStatus(StatusSinuca status) {
		this.status = status;
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

	public ClienteResumoModal getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResumoModal cliente) {
		this.cliente = cliente;
	}

	public Integer getFichasDevedor() {
		return fichasDevedor;
	}

	public void setFichasDevedor(Integer fichasDevedor) {
		this.fichasDevedor = fichasDevedor;
	}

	public Double getVlrTotalFichasDevedor() {
		return vlrTotalFichasDevedor;
	}

	public void setVlrTotalFichasDevedor(Double vlrTotalFichasDevedor) {
		this.vlrTotalFichasDevedor = vlrTotalFichasDevedor;
	}
	
	
	
}
