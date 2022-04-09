package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public List<Restaurant> list() {
		return restaurantService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> searchById(@PathVariable Long id) {

		Restaurant restaurant = restaurantService.searchById(id);

		if (restaurant != null) {
			return ResponseEntity.ok(restaurant);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.save(restaurant);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		try {
			Restaurant restaurantActual = restaurantService.searchById(id);
			
			if (restaurantActual != null) {

				BeanUtils.copyProperties(restaurant, restaurantActual, "id");
				restaurantActual = restaurantService.save(restaurantActual);

				return ResponseEntity.ok(restaurantActual);

			}
			return ResponseEntity.notFound().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePartial(@PathVariable Long id, @RequestBody Map<String, Object> attrs){
		
		Restaurant restaurantActual = restaurantService.searchById(id);
		
		if(restaurantActual == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(attrs, restaurantActual);
		
		return update(id, restaurantActual);
	}

	private void merge(Map<String, Object> attrs, Restaurant restaurantDestiny) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant restaurantOrigin = objectMapper.convertValue(attrs, Restaurant.class);
		
		
		attrs.forEach((propName, propValue) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, propName);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
			
			ReflectionUtils.setField(field, restaurantDestiny, newValue);
			
		});
	}

}
