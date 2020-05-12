package br.com.outlook.langsdorf.cei4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.outlook.langsdorf.cei4j.factory.impl.WebScrapingFactoryImpl;
import br.com.outlook.langsdorf.cei4j.model.Response;
import br.com.outlook.langsdorf.cei4j.model.User;

@Controller
public class ResponseController {

	@GetMapping(path = "/get", produces = { "application/json" })
	public ResponseEntity<Response> create(RequestEntity<String> requestEntity) {
		try {
			WebScrapingFactoryImpl ws = new WebScrapingFactoryImpl();
			String username = requestEntity.getHeaders().getFirst("username");
			String password = requestEntity.getHeaders().getFirst("password");
			User user = ws.createFullUser(username, password);
			return ResponseEntity.ok(ws.createResponse(user));
		} catch (Exception e) {
			System.out.println("JSON fields are not parsable." + e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

}
