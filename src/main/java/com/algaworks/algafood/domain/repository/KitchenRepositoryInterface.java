package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;

public interface KitchenRepositoryInterface {

	List<Kitchen> all();
	List<Kitchen> findByName(String name);
	Kitchen byId(Long id);
	
	Kitchen add(Kitchen kitchen);
	void remove(Long id);	
}
