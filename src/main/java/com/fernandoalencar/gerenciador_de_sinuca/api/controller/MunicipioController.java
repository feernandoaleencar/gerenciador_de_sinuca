package com.fernandoalencar.gerenciador_de_sinuca.api.controller;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Municipio;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Municipio> pesquisar(@RequestParam Long estado) {
        return municipioRepository.findByEstadoCodigo(estado);
    }
}
