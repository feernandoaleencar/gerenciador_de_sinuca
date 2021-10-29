package com.fernandoalencar.gerenciador_de_sinuca.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class SinucaFilter {

	private Long id;

	private Integer patrimonio;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private OffsetDateTime dataAberturaDe;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private OffsetDateTime dataAberturaAte;

	public Integer getPatrimonio() {
		return patrimonio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPatrimonio(Integer patrimonio) {
		this.patrimonio = patrimonio;
	}

	public OffsetDateTime getDataAberturaDe() {
		return dataAberturaDe;
	}

	public void setDataAberturaDe(OffsetDateTime dataAberturaDe) {
		this.dataAberturaDe = dataAberturaDe;
	}

	public OffsetDateTime getDataAberturaAte() {
		return dataAberturaAte;
	}

	public void setDataAberturaAte(OffsetDateTime dataAberturaAte) {
		this.dataAberturaAte = dataAberturaAte;
	}

}
