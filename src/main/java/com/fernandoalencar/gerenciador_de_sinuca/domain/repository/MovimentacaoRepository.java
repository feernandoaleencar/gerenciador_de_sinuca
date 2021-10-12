package com.fernandoalencar.gerenciador_de_sinuca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}
