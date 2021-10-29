package com.fernandoalencar.gerenciador_de_sinuca.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernandoalencar.gerenciador_de_sinuca.domain.exception.NegocioException;

@Entity
public class Sinuca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Cliente cliente;

	@JsonFormat(pattern =  "dd-MM-yyyy")
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFechamento;

	@Enumerated(EnumType.STRING)
	private StatusSinuca status;

	@NotNull
	private Integer contadorFicha;

	@NotNull
	private Double vlrFicha;

	@NotNull
	private Double porcentagemEmpresa;

	@NotNull
	private Double porcentagemCliente;

	@NotNull
	private Integer patrimonio;
	
	@OneToMany(mappedBy = "sinuca")
	@JsonIgnore
	private List<Movimentacao> movimentacoes = new ArrayList<>();

	private Integer fichasDevedor;
	
	private Double vlrTotalFichasDevedor;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public OffsetDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(OffsetDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getContadorFicha() {
		return contadorFicha;
	}

	public void setContadorFicha(Integer contadorFicha) {
		this.contadorFicha = contadorFicha;
	}

	public Double getVlrFicha() {
		return vlrFicha;
	}

	public void setVlrFicha(Double vlrFicha) {
		this.vlrFicha = vlrFicha;
	}

	public Double getPorcentagemEmpresa() {
		return porcentagemEmpresa;
	}

	public void setPorcentagemEmpresa(Double porcentagemEmpresa) {
		this.porcentagemEmpresa = porcentagemEmpresa;
	}

	public Double getPorcentagemCliente() {
		return porcentagemCliente;
	}

	public void setPorcentagemCliente(Double porcentagemCliente) {
		this.porcentagemCliente = porcentagemCliente;
	}

	public Integer getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Integer patrimonio) {
		this.patrimonio = patrimonio;
	}

	public StatusSinuca getStatus() {
		return status;
	}

	public void setStatus(StatusSinuca status) {
		this.status = status;
	}

	public Integer getFichasDevedor() {
		return fichasDevedor;
	}

	public void setFichasDevedor(Integer fichasDevedor) {
		this.fichasDevedor = fichasDevedor;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public Double getvlrTotalFichasDevedor() {
		return vlrTotalFichasDevedor;
	}

	public void setvlrTotalFichasDevedor(Double vlrTotalFichasDevedor) {
		this.vlrTotalFichasDevedor = vlrTotalFichasDevedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Sinuca other = (Sinuca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean podeFicarDisponivel() {
		return StatusSinuca.MANUTENCAO.equals(getStatus()) || StatusSinuca.QUEBRADA.equals(getStatus());
	}

	public boolean naoPodeFicarDisponivel() {
		return !podeFicarDisponivel();
	}
	
	public void ficarDisponivel() {
		
		if (naoPodeFicarDisponivel()) {
			throw new NegocioException("Sinuca não pode ficar disponível, verifique o status!");
		}
		
		setStatus(StatusSinuca.DISPONIVEL);
		
	}

	public boolean podeSerEncerrada() {
		return StatusSinuca.ALUGADA.equals(getStatus());
	}

	public boolean naoPodeSerEncerrada() {
		return !podeSerEncerrada();
	}

	public void finalizar() {

		if (naoPodeSerEncerrada()) {
			throw new NegocioException("Sinuca não pode ser encerrada, verifique o status!");
		}

		setStatus(StatusSinuca.ENCERRADA);
		setDataFechamento(OffsetDateTime.now());

	}

	public boolean podeSerAlugada() {
		return StatusSinuca.DISPONIVEL.equals(getStatus());
	}

	public boolean naoPodeSerAlugada() {
		return !podeSerAlugada();
	}

	public void alugar() {
		
		if (naoPodeSerAlugada()) {
			throw new NegocioException("Sinuca não pode ser alugada, verifique o status!");
		}
		
		setStatus(StatusSinuca.ALUGADA);

	}
	
	public boolean podeFazerManutencao() {
		return StatusSinuca.ALUGADA.equals(getStatus()) || StatusSinuca.QUEBRADA.equals(getStatus());
	}

	public boolean naoPodeFazerManutencao() {
		return !podeFazerManutencao();
	}

	public void fazerManutencao() {
		
		if (naoPodeFazerManutencao()) {
			throw new NegocioException("Sinuca não pode sofrer manutencao, verifique o status!");
		}
		
		setStatus(StatusSinuca.MANUTENCAO);
	}
	
	public boolean podeQuebrar() {
		return StatusSinuca.ALUGADA.equals(getStatus());
	}

	public boolean naoPodeQuebrar() {
		return !podeQuebrar();
	}
	
	public void quebrarSinuca() {
		if (naoPodeFazerManutencao()) {
			throw new NegocioException("Sinuca não pode ser quebrada, verifique o status!");
		}
		
		setStatus(StatusSinuca.QUEBRADA);
	}

	

}
