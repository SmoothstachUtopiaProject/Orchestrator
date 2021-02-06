package com.ss.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final String PATH = "http://user-service/users";

	@Autowired
	RestTemplate restTemplate;

	@GetMapping
	public ResponseEntity<Object> findAll() {
		try {
			RequestEntity<Void> request = RequestEntity.get(PATH).accept(MediaType.APPLICATION_JSON).build();
			return restTemplate.exchange(request, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id) {
		try {
			RequestEntity<Void> request = RequestEntity.get(PATH + "/" + id).accept(MediaType.APPLICATION_JSON).build();
			return restTemplate.exchange(request, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
	
	

//	@RequestMapping(path = "/book/hello", method = RequestMethod.GET)
//	public String bookHello() {
//		return restTemplate.getForEntity("http://book-service/book/hello", String.class).getBody();
//	}
//	
//	
//	@RequestMapping(path = "/author/hello", method = RequestMethod.GET)
//	public String authorHello() {
//		return restTemplate.getForEntity("http://author-service/author/hello", String.class).getBody();
//	}

}
