package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepositoryInterface;

@Repository
public class CityRepositoryImpl implements CityRepositoryInterface{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<City> all() {
		return manager.createQuery("from City", City.class).getResultList();
	}

	@Override
	public City byId(Long id) {
		return manager.find(City.class, id);
	}

	@Override
	@Transactional
	public City add(City city) {
		return manager.merge(city);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		City city = byId(id);
		
		if(city == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(city);
	}
	
}
