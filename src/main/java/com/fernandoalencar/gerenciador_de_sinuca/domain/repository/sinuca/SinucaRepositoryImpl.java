package com.fernandoalencar.gerenciador_de_sinuca.domain.repository.sinuca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca_;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.StatusSinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.filter.SinucaFilter;

public class SinucaRepositoryImpl implements SinucaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Sinuca> filtrar(SinucaFilter sinucaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Sinuca> criteria = builder.createQuery(Sinuca.class);
		Root<Sinuca> root = criteria.from(Sinuca.class);

		Predicate[] predicates = criarRestricoes(sinucaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Sinuca> query = manager.createQuery(criteria);

		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(sinucaFilter));
	}

	private Predicate[] criarRestricoes(SinucaFilter sinucaFilter, CriteriaBuilder builder, Root<Sinuca> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(sinucaFilter.getPatrimonio())) {
			predicates
					.add(builder.equal(builder.toInteger(root.get(Sinuca_.patrimonio)), sinucaFilter.getPatrimonio()));
		}

		if (!ObjectUtils.isEmpty(sinucaFilter.getDataAberturaDe())) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Sinuca_.dataAbertura), sinucaFilter.getDataAberturaDe()));
		}

		if (!ObjectUtils.isEmpty(sinucaFilter.getDataAberturaAte())) {
			predicates
					.add(builder.lessThanOrEqualTo(root.get(Sinuca_.dataAbertura), sinucaFilter.getDataAberturaAte()));
		}

		/*
		 * if(!ObjectUtils.isEmpty(sinucaFilter.getStatus())) {
		 * predicates.add(builder.like( //builder.lower(root.get(Sinuca_.status)),
		 * sinucaFilter.getStatus())); builder.lower(root.get(Sinuca_.status)),
		 * sinucaFilter.getStatus())); }
		 */

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Sinuca> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(SinucaFilter sinucaFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Sinuca> root = criteria.from(Sinuca.class);

		Predicate[] predicates = criarRestricoes(sinucaFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
