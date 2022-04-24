package com.fernandoalencar.gerenciador_de_sinuca.domain.repository;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    List<Municipio> findByEstadoCodigo(Long estadoCodigo);

}
