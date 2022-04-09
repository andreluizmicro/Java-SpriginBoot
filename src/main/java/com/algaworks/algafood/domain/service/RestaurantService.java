package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryInterface;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepositoryInterface restaurantRepository;
	
	@Autowired
	private KitchenRepositoryInterface kitchenRepository;
	
	
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		
		Kitchen kitchen = kitchenRepository.byId(kitchenId);
		
		if(kitchen == null) {
			throw new EntityNotFoundException(
					String.format("Não existe cadastro de cozinha com código %d", kitchenId));
		}
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.add(restaurant);
	}
	
	public List<Restaurant> list(){
		return restaurantRepository.all();
	}
	
	public Restaurant searchById(Long id) {
		return restaurantRepository.byId(id);
	}
	
}
