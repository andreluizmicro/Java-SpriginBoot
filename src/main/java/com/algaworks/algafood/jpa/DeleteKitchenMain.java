package com.algaworks.algafood.jpa;



import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;

public class DeleteKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepositoryInterface kitchens = applicationContext.getBean(KitchenRepositoryInterface.class);
		
		Kitchen cozinha =  new Kitchen();
		cozinha.setId(1L);
		
		kitchens.remove(cozinha.getId());
	}
}
