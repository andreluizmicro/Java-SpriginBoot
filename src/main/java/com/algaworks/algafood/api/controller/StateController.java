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
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {
		
	@Autowired
	private StateService stateService;
	
	@GetMapping
	public List<State> list(){
		return stateService.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<State> searchByid(@PathVariable Long id){
		State state = stateService.searchById(id);
		
		if(state != null) {
			return ResponseEntity.ok(state);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State add(@RequestBody State state){
		return stateService.save(state);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state){
		State stateActual = stateService.searchById(id);
		
		if(stateActual != null) {
			BeanUtils.copyProperties(state, stateActual, "id");
			stateActual = stateService.save(stateActual);

			return ResponseEntity.ok(stateActual);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable long id){
		try {
			stateService.remove(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
