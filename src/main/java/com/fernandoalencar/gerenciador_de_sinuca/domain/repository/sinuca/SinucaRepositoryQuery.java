package com.fernandoalencar.gerenciador_de_sinuca.domain.repository.sinuca;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.filter.SinucaFilter;

@Repository
public interface SinucaRepositoryQuery {
	
	public Page<Sinuca> filtrar(SinucaFilter sinucaFilter, Pageable pageable);
	
}
