package com.algaworks.algafood.jpa;



import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepositoryInterface;

public class UpdateKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepositoryInterface crudKitchen = applicationContext.getBean(KitchenRepositoryInterface.class);
		
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Brasileira");
		
		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japonesa");
		
		crudKitchen.add(kitchen1);
		crudKitchen.add(kitchen2);
		
		System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
		System.out.printf("%d - %s\n", kitchen2.getId(), kitchen1.getName());
	}
}
