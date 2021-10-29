package com.fernandoalencar.gerenciador_de_sinuca.domain.repository.filter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class SinucaFilter {

	private Long id;

	private Integer patrimonio;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataAberturaDe;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataAberturaAte;

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

	public LocalDate getDataAberturaDe() {
		return dataAberturaDe;
	}

	public void setDataAberturaDe(LocalDate dataAberturaDe) {
		this.dataAberturaDe = dataAberturaDe;
	}

	public LocalDate getDataAberturaAte() {
		return dataAberturaAte;
	}

	public void setDataAberturaAte(LocalDate dataAberturaAte) {
		this.dataAberturaAte = dataAberturaAte;
	}

}
