package com.jmora.wchallenger.services;

import java.util.List;

import com.jmora.wchallenger.domain.Permission;

/**
 * 
 * @author joseluismoravilladiego
 *
 */
public interface PermissionService {

	public List<Permission> retrieve();

	public Permission get(Long id);

	public void save(Permission permission);

	public void delete(Long id);

	public void update(Permission permission);

	public List<Permission> findByAlbumId(Long albumId);
}
