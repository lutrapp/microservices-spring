package com.microservices.apishop.repositoryImpl;

import java.time.LocalDate;
import java.util.List;

import com.microservices.apishop.dto.ShopReportDto;
//import com.microservices.apishop.dto.ShopReportDto;
import com.microservices.apishop.model.Shop;
import com.microservices.apishop.repository.ReportRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class ReportRepositoryImpl implements ReportRepository {
	@PersistenceContext
	private EntityManager entityManager;
	// a implementação das consultas vai aqui

	@Override
	public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
		StringBuilder sb = new StringBuilder();
		sb.append("select s ");
		sb.append("from	shop s ");
		sb.append("where s.date	>= :dataInicio ");
		if (dataFim != null) {
			sb.append("and s.date <= :dataFim ");
		}
		if (valorMinimo != null) {
			sb.append("and s.total <= :valorMinimo ");
		}
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("dataInicio", dataInicio.atTime(0, 0));
		if (dataFim != null) {
			query.setParameter("dataFim", dataFim.atTime(23, 59));
		}
		if (valorMinimo != null) {
			query.setParameter("valorMinimo", valorMinimo);
		}
		return query.getResultList();
	}
//TODO dividir os metodos em partes menores

	
	@Override
	public ShopReportDto getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(sp.id),	sum(sp.total), avg(sp.total) ");
		sb.append("from	shopping.shop sp ");
		sb.append("where sp.date >= :dataInicio	");
		sb.append("and sp.date <= :dataFim ");
		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter("dataInicio", dataInicio.atTime(0, 0));
		query.setParameter("dataFim", dataFim.atTime(23, 59));
		
		Object[] result = (Object[]) query.getSingleResult();
		ShopReportDto shopReportDto = new ShopReportDto();
		shopReportDto.setCount(((Long) result[0]).intValue());
		shopReportDto.setTotal((Double) result[1]);
		shopReportDto.setMean((Double) result[2]);
		return shopReportDto;
	}
}