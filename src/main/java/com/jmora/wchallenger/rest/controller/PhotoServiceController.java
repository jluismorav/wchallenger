package com.jmora.wchallenger.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jmora.wchallenger.App;
import com.jmora.wchallenger.domain.Album;
import com.jmora.wchallenger.domain.Photo;

@RestController
public class PhotoServiceController {
	protected static final String PATH = "/photos";

	@Autowired
	private AlbumServiceController albumServiceController;

	@GetMapping(path = PhotoServiceController.PATH + "/")
	public List<Photo> getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Photo>> response = restTemplate.exchange(
				App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM + PhotoServiceController.PATH, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Photo>>() {
				});
		return response.getBody();

	}

	@GetMapping(path = PhotoServiceController.PATH + "/{id}")
	public Photo get(@PathVariable Long id) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder buffer = new StringBuilder(App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM)
				.append(PhotoServiceController.PATH).append("/").append(id);
		return restTemplate.getForEntity(buffer.toString(), Photo.class).getBody();

	}

	@GetMapping(path = PhotoServiceController.PATH)
	public List<Photo> getByAlbumId(@RequestParam(name = "albumId") Long albumId) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(App.HTTPS_JSONPLACEHOLDER_TYPICODE_COM + PhotoServiceController.PATH)
				.queryParam("albumId", albumId);
		ResponseEntity<List<Photo>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Photo>>() {
				}, albumId);
		return response.getBody();

	}

	@GetMapping(path = "/users/{userId}/photos")
	public List<Photo> getByUser(@PathVariable Long userId) {
		List<Album> albums = albumServiceController.getByUserId(userId);
		List<Photo> listPhotos = new ArrayList<>();
		for (Album album : albums) {
			listPhotos.addAll(this.getByAlbumId(album.getId()));
		}
		return listPhotos;

	}
}
