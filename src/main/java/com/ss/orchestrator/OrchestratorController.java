package com.ss.orchestrator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrchestratorController {

	private final String SERVICE_PATH_ORCHESTRATOR = "http://localhost:8080";
	private final String SERVICE_PATH_AIRPORTS = "http://AIRPORT-SERVICE";
	private final String SERVICE_PATH_ROUTES = "http://ROUTE-SERVICE";
	private final String SERVICE_PATH_USERS = "http://USER-SERVICE";

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(path = { "/airports", "/airports/*" })
	public ResponseEntity<Object> airports(RequestEntity<Object> incomingRequest) {
		String newURI = incomingRequest.getUrl().toString().replace(SERVICE_PATH_ORCHESTRATOR, SERVICE_PATH_AIRPORTS);
		return rerouteToService(incomingRequest, newURI);
	}

	@RequestMapping(path = { "/routes", "/routes/*" })
	public ResponseEntity<Object> routes(RequestEntity<Object> incomingRequest) {
		String newURI = incomingRequest.getUrl().toString().replace(SERVICE_PATH_ORCHESTRATOR, SERVICE_PATH_ROUTES);
		return rerouteToService(incomingRequest, newURI);
	}

	// produces = { "application/json", "application/xml", "text/xml"}, consumes = MediaType.ALL_VALUE
	@RequestMapping(path = { "/users", "/users/**" })
	public ResponseEntity<Object> users(RequestEntity<Object> incomingRequest) {
		String newURI = incomingRequest.getUrl().toString().replace(SERVICE_PATH_ORCHESTRATOR, SERVICE_PATH_USERS);
		return rerouteToService(incomingRequest, newURI);
	}

	private ResponseEntity<Object> rerouteToService(RequestEntity<Object> incomingRequest, String newURI) {
		RequestEntity<Object> outgoingRequest = RequestEntity.method(incomingRequest.getMethod(), newURI)
				.accept(MediaType.ALL).headers(incomingRequest.getHeaders())
				.body(incomingRequest.getBody());

		try {
			return restTemplate.exchange(outgoingRequest, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
	
	
//	
//	List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
//	//Add the Jackson Message converter
//	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//	// Note: here we are making this converter to process any kind of response, 
//	// not only application/*json, which is the default behaviour
//	converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
//	messageConverters.add(converter);  
//	restTemplate.setMessageConverters(messageConverters); 
	
	
	
	
	
}