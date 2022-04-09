package com.algaworks.algafood.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryInterface;

public class SearchRestaurantMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestaurantRepositoryInterface restaurants = applicationContext.getBean(RestaurantRepositoryInterface.class);
		
		List<Restaurant> allRestaurants = restaurants.all();
		
	
		for (Restaurant restaurant : allRestaurants) {
			System.out.printf("%s - %f - %s", restaurant.getName(), restaurant.getShippingFee(), restaurant.getKitchen().getName() + "\n");
		}
	}
}
