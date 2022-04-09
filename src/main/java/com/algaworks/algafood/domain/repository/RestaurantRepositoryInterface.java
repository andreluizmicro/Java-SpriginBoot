package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurant;

public interface RestaurantRepositoryInterface {

	List<Restaurant> all();
	Restaurant byId(Long id);
	Restaurant add(Restaurant restaurant);
	void remove(Restaurant restaurant);
}
