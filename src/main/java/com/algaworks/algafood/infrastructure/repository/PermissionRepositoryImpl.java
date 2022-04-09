package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.PermissionRepositoryInterface;

@Repository
public class PermissionRepositoryImpl implements PermissionRepositoryInterface {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permission> all() {
		return manager.createQuery("from permission", Permission.class).getResultList();
	}

	@Override
	public Permission byId(Long id) {
		return manager.find(Permission.class, id);
	}

	@Override
	@Transactional
	public Permission add(Permission permission) {
		return manager.merge(permission);
	}

	@Override
	@Transactional
	public void remove(Permission permission) {
		permission = byId(permission.getId());
		manager.remove(permission);
	}

}
