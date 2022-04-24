package com.fernandoalencar.gerenciador_de_sinuca.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @Column(name = "codigo_ibge")
    private Long codigoIbge;

    private String nome;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean capital;
    private String siafiId;
    private Long ddd;
    private String fuso_horario;

    @ManyToOne
    @JoinColumn(name = "codigo_uf")
    private Estado estado;

    public Long getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Long codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public String getSiafiId() {
        return siafiId;
    }

    public void setSiafiId(String siafiId) {
        this.siafiId = siafiId;
    }

    public Long getDdd() {
        return ddd;
    }

    public void setDdd(Long ddd) {
        this.ddd = ddd;
    }

    public String getFuso_horario() {
        return fuso_horario;
    }

    public void setFuso_horario(String fuso_horario) {
        this.fuso_horario = fuso_horario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoIbge == null) ? 0 : codigoIbge.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Municipio other = (Municipio) obj;
        if (codigoIbge == null) {
            if (other.codigoIbge != null)
                return false;
        } else if (!codigoIbge.equals(other.codigoIbge))
            return false;
        return true;
    }
}
