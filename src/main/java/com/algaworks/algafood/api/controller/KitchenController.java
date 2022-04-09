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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;
import com.algaworks.algafood.domain.service.KitchenService;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepositoryInterface kitchenRepository;

	@Autowired
	private KitchenService kitchenService;

	@GetMapping
	public List<Kitchen> list() {
		return kitchenRepository.all();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> searchById(@PathVariable Long id) {
		Kitchen kitchen = kitchenRepository.byId(id);

		if (kitchen != null) {
			return ResponseEntity.ok(kitchen);
		}

//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen add(@RequestBody Kitchen kitchen) {
		return kitchenService.add(kitchen);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
		Kitchen kitchenActual = kitchenRepository.byId(id);

		if (kitchenActual != null) {
			BeanUtils.copyProperties(kitchen, kitchenActual, "id");
			kitchenActual = kitchenService.add(kitchenActual);

			return ResponseEntity.ok(kitchenActual);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Kitchen> remove(@PathVariable Long id) {
		try {
			kitchenService.remove(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
