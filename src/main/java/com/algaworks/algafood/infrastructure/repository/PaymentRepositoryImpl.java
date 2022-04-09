package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Payment;
import com.algaworks.algafood.domain.repository.PaymentRepositoryInterface;

@Repository
public class PaymentRepositoryImpl implements PaymentRepositoryInterface{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Payment> all() {
		return manager.createQuery("payment", Payment.class).getResultList();
	}

	@Override
	public Payment byId(Long id) {
		return manager.find(Payment.class, id);
	}

	@Override
	@Transactional
	public Payment add(Payment payment) {
		return manager.merge(payment);
	}

	@Override
	@Transactional
	public void remove(Payment payment) {
		payment = byId(payment.getId());
		manager.remove(payment);
	}

}
