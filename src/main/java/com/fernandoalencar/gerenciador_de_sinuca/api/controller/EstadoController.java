package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Estado;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }
}
