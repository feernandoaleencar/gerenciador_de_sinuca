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

import org.springframework.util.ObjectUtils;

import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca;
import com.fernandoalencar.gerenciador_de_sinuca.domain.model.Sinuca_;
import com.fernandoalencar.gerenciador_de_sinuca.domain.repository.filter.SinucaFilter;

public class SinucaRepositoryImpl implements SinucaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Sinuca> filtrar(SinucaFilter sinucaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Sinuca> criteria = builder.createQuery(Sinuca.class);
		Root<Sinuca> root = criteria.from(Sinuca.class);

		Predicate[] predicates = criarRestricoes(sinucaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Sinuca> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	private Predicate[] criarRestricoes(SinucaFilter sinucaFilter, CriteriaBuilder builder, Root<Sinuca> root) {

		List<Predicate> predicates = new ArrayList<>();
		

		if (!ObjectUtils.isEmpty(sinucaFilter.getPatrimonio())) {
			predicates.add(builder.equal(
					builder.toInteger(root.get(Sinuca_.patrimonio)), sinucaFilter.getPatrimonio()));
		}

		if (!ObjectUtils.isEmpty(sinucaFilter.getDataAberturaDe())) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Sinuca_.dataAbertura), sinucaFilter.getDataAberturaDe()));
		}

		if (!ObjectUtils.isEmpty(sinucaFilter.getDataAberturaAte())) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Sinuca_.dataAbertura), sinucaFilter.getDataAberturaAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}