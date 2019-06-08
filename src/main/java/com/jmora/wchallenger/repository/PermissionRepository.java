package com.jmora.wchallenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmora.wchallenger.domain.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	List<Permission> findByAlbumId(Long albumId);

}
