package com.fernandoalencar.gerenciador_de_sinuca.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sinuca.class)
public abstract class Sinuca_ {

	public static volatile SingularAttribute<Sinuca, Long> id;
	public static volatile SingularAttribute<Sinuca, Cliente> cliente;
	public static volatile SingularAttribute<Sinuca, LocalDate> dataAbertura;
	public static volatile SingularAttribute<Sinuca, LocalDate> dataFechamento;
	public static volatile SingularAttribute<Sinuca, StatusSinuca> status;
	public static volatile SingularAttribute<Sinuca, Long> contadorFicha;
	public static volatile SingularAttribute<Sinuca, Double> vlrFicha;
	public static volatile SingularAttribute<Sinuca, Double> porcentagemEmpresa;
	public static volatile SingularAttribute<Sinuca, Double> porcentagemCliente;
	public static volatile SingularAttribute<Sinuca, Integer> patrimonio;
	public static volatile SingularAttribute<Sinuca, List<Movimentacao>> movimentacoes;
	public static volatile SingularAttribute<Sinuca, Integer> fichasDevedor;
	public static volatile SingularAttribute<Sinuca, Double> vlrTotalFichasDevedor;

}

