package com.jmora.wchallenger.rest.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jmora.wchallenger.App;
import com.jmora.wchallenger.domain.User;

@RestController
@RequestMapping(path = UserServiceController.PATH)
public class UserServiceController {
	protected static final String PATH = "/users";

	@GetMapping(path = "/", produces = "application/json")
	public List<User> getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<User>> response = restTemplate.exchange(
				App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM + UserServiceController.PATH, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		return response.getBody();

	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public User get(@PathVariable Long id) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder buffer = new StringBuilder(App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM)
				.append(UserServiceController.PATH).append("/").append(id);
		return restTemplate.getForEntity(buffer.toString(), User.class).getBody();

	}
}
