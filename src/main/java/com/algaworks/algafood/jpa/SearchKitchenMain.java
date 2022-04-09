package com.algaworks.algafood.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;

public class SearchKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepositoryInterface kitchens = applicationContext.getBean(KitchenRepositoryInterface.class);
		
		List<Kitchen> allKitchens = kitchens.all();
		
	
		for (Kitchen kitchen : allKitchens) {
			System.out.println(kitchen.getName());
		}
	}
}
