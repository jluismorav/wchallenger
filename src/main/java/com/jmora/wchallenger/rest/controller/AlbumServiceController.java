package com.jmora.wchallenger.rest.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jmora.wchallenger.App;
import com.jmora.wchallenger.domain.Album;

@RestController
@RequestMapping(path = AlbumServiceController.PATH)
public class AlbumServiceController {
	protected static final String PATH = "/albums";

	@GetMapping(path = "/", produces = "application/json")
	public List<Album> getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Album>> response = restTemplate.exchange(
				App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM + AlbumServiceController.PATH, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});
		return response.getBody();

	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public Album get(@PathVariable Long id) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder buffer = new StringBuilder(App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM)
				.append(AlbumServiceController.PATH).append("/").append(id);
		return restTemplate.getForEntity(buffer.toString(), Album.class).getBody();

	}

	@GetMapping(produces = "application/json")
	public List<Album> getByUserId(@RequestParam(required = false, name = "userId") Long userId) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM + AlbumServiceController.PATH)
				.queryParam("userId", userId);
		ResponseEntity<List<Album>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				}, userId);
		return response.getBody();

	}
}
