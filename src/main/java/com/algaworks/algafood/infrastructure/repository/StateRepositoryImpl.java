package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepositoryInterface;

@Repository
public class StateRepositoryImpl implements StateRepositoryInterface{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<State> all() {
		return manager.createQuery("from State", State.class).getResultList();
	}

	@Override
	public State byId(Long id) {
		return manager.find(State.class, id);
	}

	@Override
	@Transactional
	public State add(State state) {
		return manager.merge(state);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		State state = byId(id);
		
		if(state == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		
		manager.remove(state);
	}

}
