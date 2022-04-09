package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private KitchenRepositoryInterface kitchenRepository;
	
	@GetMapping("/kitchens/por-nome")
	public List<Kitchen> kitchenByName(@RequestParam("name") String name){
		return kitchenRepository.findByName(name);
	}
	
}
