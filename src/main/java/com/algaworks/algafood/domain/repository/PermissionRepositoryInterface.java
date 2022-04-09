package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Permission;

public interface PermissionRepositoryInterface {

	List<Permission> all();
	Permission byId(Long id);
	Permission add(Permission permission);
	void remove(Permission permission);
}
