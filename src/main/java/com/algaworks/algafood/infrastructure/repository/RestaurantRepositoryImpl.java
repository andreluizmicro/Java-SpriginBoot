package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryInterface;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryInterface{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> all() {
		return manager.createQuery("from Restaurant", Restaurant.class).getResultList();
	}

	@Override
	public Restaurant byId(Long id) {
		return manager.find(Restaurant.class, id);
	}

	@Override
	@Transactional
	public Restaurant add(Restaurant restaurant) {
		return manager.merge(restaurant);
	}

	@Override
	@Transactional
	public void remove(Restaurant restaurant) {
		restaurant = byId(restaurant.getId());
		manager.remove(restaurant);
	}

}
