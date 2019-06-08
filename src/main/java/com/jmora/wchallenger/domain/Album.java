package com.jmora.wchallenger.domain;

public class Album extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218938431975874473L;
	private Long id;
	private Long userId;
	private String title;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
