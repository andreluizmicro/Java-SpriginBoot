package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.City;

public interface CityRepositoryInterface {

	List<City> all();
	City byId(Long id);
	City add(City city);
	void remove(Long id);

}
