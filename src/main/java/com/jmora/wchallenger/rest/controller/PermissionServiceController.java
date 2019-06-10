package com.jmora.wchallenger.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmora.wchallenger.domain.Permission;
import com.jmora.wchallenger.domain.User;
import com.jmora.wchallenger.services.PermissionService;

@RestController
public class PermissionServiceController {
	protected static final String PATH = "/permissions";
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserServiceController userServiceController;

	public void setpermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@GetMapping(PATH)
	public List<Permission> get() {
		return permissionService.retrieve();
	}

	@PostMapping(PATH)
	public Permission save(Permission permission) {
		permissionService.save(permission);
		return permission;
	}

	@PutMapping(PATH + "/{id}")

	public Permission update(@RequestBody Permission permission, @PathVariable(name = "id") Long id) {
		Permission permissionFound = permissionService.get(id);
		if (permissionFound != null) {
			permissionService.update(permission);
		}
		return permission;
	}

	@GetMapping(path = PATH, params = { "albumId" })
	public List<User> getByAlbumId(@RequestParam(name = "albumId") Long albumId) {
		List<Permission> list = permissionService.findByAlbumId(albumId);
		List<User> users = new ArrayList<>();
		for (Permission permission : list) {
			users.add(userServiceController.get(permission.getUserId()));
		}
		return users;

	}

}
