package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;

@Repository
public class KitchenRepositoryImpl implements KitchenRepositoryInterface {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Kitchen> all(){
		return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
	}
	
	@Override
	public List<Kitchen> findByName(String name){
		return manager.createQuery("from Kitchen where name like :name", Kitchen.class)
				.setParameter("name", "%" + name + "%") 
				.getResultList();
	}
	
	@Override
	public Kitchen byId(Long id) {
		return manager.find(Kitchen.class, id);
	}
	
	@Override
	@Transactional
	public Kitchen add(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
	
	@Override
	@Transactional
	public void remove(Long id) {
		Kitchen kitchen = byId(id);
		
		if(kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(kitchen);
	}

}
