package com.algaworks.algafood.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;

@Service
public class KitchenService {

	@Autowired
	private KitchenRepositoryInterface kitchenRepository;
	
	public Kitchen add(Kitchen kitchen) {
		return kitchenRepository.add(kitchen);
	}
	
	public void remove(Long id) {
		try {
			kitchenRepository.remove(id);	
			
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Não existe um cadastro de cozinha com código %d", id));
			
		}catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
	}
}
