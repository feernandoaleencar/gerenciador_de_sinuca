package com.fernandoalencar.gerenciador_de_sinuca.domain.repository;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
