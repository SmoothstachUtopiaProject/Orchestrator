package com.ss.orchestrator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrchestratorController {

	private final String SERVICE_PATH_AIRPORTS = "http://AIRPORT-SERVICE/airports";
	private final String SERVICE_PATH_ROUTES = "http://ROUTE-SERVICE/routes";
	private final String SERVICE_PATH_USERS = "http://USER-SERVICE/users";

  @Autowired
	RestTemplate restTemplate;
  
  @RequestMapping(path = { "/airports", "/airports/*" })
	public ResponseEntity<Object> airports(HttpServletRequest servletRequest) {
		try {
			String requestPath = servletRequest.getServletPath();
			String pathTail = requestPath.replace("/airports", "");
			RequestEntity<Void> request = RequestEntity.get(SERVICE_PATH_AIRPORTS + pathTail)
			.accept(MediaType.APPLICATION_JSON).build();
			return restTemplate.exchange(request, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}

	@RequestMapping(path = { "/routes", "/routes/*" })
	public ResponseEntity<Object> routes(HttpServletRequest servletRequest) {
		try {
			String requestPath = servletRequest.getServletPath();
			String pathTail = requestPath.replace("/routes", "");
			RequestEntity<Void> request = RequestEntity.get(SERVICE_PATH_ROUTES + pathTail)
			.accept(MediaType.APPLICATION_JSON).build();
			return restTemplate.exchange(request, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}

	@RequestMapping(path = { "/users", "/users/*" })
	public ResponseEntity<Object> users(HttpServletRequest servletRequest) {
		try {
			String requestPath = servletRequest.getServletPath();
			String pathTail = requestPath.replace("/users", "");
			RequestEntity<Void> request = RequestEntity.get(SERVICE_PATH_USERS + pathTail)
			.accept(MediaType.APPLICATION_JSON).build();
			return restTemplate.exchange(request, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
}