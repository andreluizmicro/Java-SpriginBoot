package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepositoryInterface;
import com.algaworks.algafood.domain.repository.StateRepositoryInterface;

@Service
public class CityService {

	@Autowired
	private CityRepositoryInterface cityRepository;

	@Autowired
	private StateRepositoryInterface stateRepository;

	public City save(City city) {
		Long stateId = city.getState().getId();

		State state = stateRepository.byId(stateId);

		if (state == null) {
			throw new EntityNotFoundException(String.format("Não existe cadastro de estado com código %d", stateId));
		}

		city.setState(state);

		return cityRepository.add(city);
	}

	public List<City> list() {
		return cityRepository.all();
	}

	public City searchById(Long Id) {
		return cityRepository.byId(Id);
	}

	public void remove(Long id) {
		try {
			cityRepository.remove(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Não existe um cadastro de estado com código %d", id));
		}catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Estado de código %d não pode ser removido, pois está em uso", id));
		}
	}
}
