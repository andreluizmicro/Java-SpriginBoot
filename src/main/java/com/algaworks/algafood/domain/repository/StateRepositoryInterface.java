package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.State;

public interface StateRepositoryInterface {

	List<State> all();
	State byId(Long id);
	State add(State state);
	void remove(Long id);
	
}
