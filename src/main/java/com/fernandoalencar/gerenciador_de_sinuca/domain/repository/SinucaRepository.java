package com.fernandoalencar.gerenciador_de_sinuca.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;

@Repository
public interface SinucaRepository extends JpaRepository<Sinuca, Long> {
	
}
