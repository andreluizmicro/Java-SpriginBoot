package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Payment;

public interface PaymentRepositoryInterface {

	List<Payment> all();
	Payment byId(Long id);
	Payment add(Payment payment);
	void remove(Payment payment);
	
}
