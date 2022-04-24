package com.fernandoalencar.gerenciador_de_sinuca.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNome(String nome);
	Page<Cliente> findByNomeContaining(String nome, Pageable pageable);
	Cliente findByEmail(String email);
	Cliente findByCpf(String cpf);
	
}
