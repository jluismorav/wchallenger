package com.jmora.wchallenger.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PERMISSION", uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ID", "ALBUM_ID" }))
public class Permission extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4949792433745156845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "ALBUM_ID", nullable = false)
	private Long albumId;

	@Column(name = "READ", nullable = false)
	private Boolean read;

	@Column(name = "WRITE", nullable = false)
	private Boolean write;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean getWrite() {
		return write;
	}

	public void setWrite(Boolean write) {
		this.write = write;
	}

}
