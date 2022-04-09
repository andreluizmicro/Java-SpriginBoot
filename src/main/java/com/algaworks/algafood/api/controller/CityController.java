package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.service.CityService;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public List<City> list(){
		return cityService.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> searchById(@PathVariable Long id){
		City city = cityService.searchById(id);
		
		if(city != null) {
			return ResponseEntity.ok(city);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody City city){
		try {
			city = cityService.save(city);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(city);
		}catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city) {
		try {
			City cityActual = cityService.searchById(id);
			
			if(cityActual != null) {
				
				BeanUtils.copyProperties(city, cityActual, "id");
				cityActual = cityService.save(cityActual);
				
				return ResponseEntity.ok(cityActual);
			}
			
			return ResponseEntity.badRequest().build();
			
		}catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<City> remove(@PathVariable Long id) {
		try {
			cityService.remove(id);
			
			return ResponseEntity.noContent().build();
		}catch(EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}catch(EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
