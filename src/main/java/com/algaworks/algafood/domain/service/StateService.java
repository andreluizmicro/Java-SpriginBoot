package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepositoryInterface;

@Service
public class StateService {

	@Autowired
	private StateRepositoryInterface stateRepository;
	
	public List<State> list(){
		return stateRepository.all();
	}
	
	public State searchById(Long id) {
		return stateRepository.byId(id);	
	}
	
	public State save(State state) {
		return stateRepository.add(state);
	}
	
	public void remove(Long id) {
		try {
			stateRepository.remove(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Não existe um cadastro de estado com o código %d", id));
		}catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Estado de código %d não pode ser removido, pois está em uso", id));
		}
	}
}
