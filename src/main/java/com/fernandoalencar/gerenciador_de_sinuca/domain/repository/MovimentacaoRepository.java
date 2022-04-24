package com.fernandoalencar.gerenciador_de_sinuca.domain.repository;

import com.fernandoalencar.gerenciador_de_sinuca.api.dto.MovimentacaoModel;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Movimentacao;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    Page<Movimentacao> findBySinuca(Sinuca sinuca, Pageable pageable);
}
