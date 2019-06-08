package com.jmora.wchallenger.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmora.wchallenger.domain.Permission;
import com.jmora.wchallenger.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	public void setEmployeeRepository(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	@Override
	public List<Permission> retrieve() {
		return permissionRepository.findAll();
	}

	@Override
	public Permission get(Long id) {
		Optional<Permission> optional = permissionRepository.findById(id);
		return optional.orElse(null);
	}

	@Override
	public void save(Permission permission) {
		permissionRepository.save(permission);

	}

	@Override
	public void delete(Long id) {
		permissionRepository.deleteById(id);
	}

	@Override
	public void update(Permission permission) {
		permissionRepository.save(permission);

	}

	@Override
	public List<Permission> findByAlbumId(Long albumId) {

		return permissionRepository.findByAlbumId(albumId);
	}

}
