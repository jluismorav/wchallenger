package com.jmora.wchallenger.rest.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import com.jmora.wchallenger.domain.Comment;

/**
 * 
 * 
 * @author joseluismoravilladiego
 *
 */
@RestController
@RequestMapping(path = CommentServiceController.PATH)
public class CommentServiceController {
	private static final String UTF_8 = "UTF-8";
	protected static final String PATH = "/comments";
	private static final String HTTPS_JSONPLACEHOLDER_TYPICODE_COM = "https://jsonplaceholder.typicode.com";

	/**
	 * Los álbumes del sistema y de cada usuario.
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public List<Comment> getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Comment>> response = restTemplate.exchange(
				HTTPS_JSONPLACEHOLDER_TYPICODE_COM + CommentServiceController.PATH, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Comment>>() {
				});
		return response.getBody();

	}

	@GetMapping(path = "/{id}")
	public Comment get(@PathVariable Long id) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder buffer = new StringBuilder(HTTPS_JSONPLACEHOLDER_TYPICODE_COM).append(PATH).append("/")
				.append(id);
		return restTemplate.getForEntity(buffer.toString(), Comment.class).getBody();

	}

	@GetMapping(params = "postId")
	public List<Comment> getByPostId(@RequestParam(required = false, name = "postId") Long postId) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(HTTPS_JSONPLACEHOLDER_TYPICODE_COM + PATH)
				.queryParam("postId", postId);
		ResponseEntity<List<Comment>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Comment>>() {
				}, postId);
		return response.getBody();

	}

	/**
	 * En cuanto a los comentarios, se espera que la aplicación pueda traerlos del
	 * servicio externo brindando la posibilidad de filtrar por el campo “name” o
	 * por el ¿usuario que realizó dicho comentario?.
	 * 
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@GetMapping(params = "name")
	public List<Comment> getByName(@RequestParam(required = false, name = "name") String name)
			throws UnsupportedEncodingException {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(HTTPS_JSONPLACEHOLDER_TYPICODE_COM + PATH)
				.queryParam("name", URLEncoder.encode(name, UTF_8));

		ResponseEntity<List<Comment>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Comment>>() {
				}, name);
		return response.getBody();

	}
}
