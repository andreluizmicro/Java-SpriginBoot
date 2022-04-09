package com.algaworks.algafood.jpa;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;

public class CreateKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepositoryInterface createKitchen = applicationContext.getBean(KitchenRepositoryInterface.class);
		
		Kitchen kitchen = createKitchen.byId(2L);
		
	
		System.out.println("Nome da Cozinha é: " + kitchen.getName());
	}
}
