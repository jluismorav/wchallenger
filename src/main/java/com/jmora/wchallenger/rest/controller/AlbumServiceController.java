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

import com.jmora.wchallenger.domain.Album;
import com.jmora.wchallenger.domain.User;

/**
 * WEbService para gestionar {@link User}
 * 
 * @author joseluismoravilladiego
 *
 */
@RestController
@RequestMapping(path = AlbumServiceController.PATH)
public class AlbumServiceController {
	protected static final String PATH = "/albums";
	private static final String HTTPS_JSONPLACEHOLDER_TYPICODE_COM = "https://jsonplaceholder.typicode.com";

	/**
	 * Los álbumes del sistema y de cada usuario.
	 * 
	 * @return
	 */
	@GetMapping(path = "/", produces = "application/json")
	public List<Album> getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Album>> response = restTemplate.exchange(
				HTTPS_JSONPLACEHOLDER_TYPICODE_COM + AlbumServiceController.PATH, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});
		return response.getBody();

	}

	/**
	 * Los álbumes del sistema
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}", produces = "application/json")
	public Album get(@PathVariable Long id) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder buffer = new StringBuilder(HTTPS_JSONPLACEHOLDER_TYPICODE_COM).append(AlbumServiceController.PATH)
				.append("/").append(id);
		return restTemplate.getForEntity(buffer.toString(), Album.class).getBody();

	}

	/**
	 * Los álbumes del sistema de cada usuario.
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping(produces = "application/json")
	public List<Album> getByUserId(@RequestParam(required = false, name = "userId") Long userId) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(HTTPS_JSONPLACEHOLDER_TYPICODE_COM + AlbumServiceController.PATH)
				.queryParam("userId", userId);
		ResponseEntity<List<Album>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				}, userId);
		return response.getBody();

	}
}
