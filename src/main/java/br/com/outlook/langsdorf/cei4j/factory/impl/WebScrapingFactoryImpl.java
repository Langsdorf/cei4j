package br.com.outlook.langsdorf.cei4j.factory.impl;

import java.util.ArrayList;

import br.com.outlook.langsdorf.cei4j.factory.WebScrapingFactory;
import br.com.outlook.langsdorf.cei4j.model.Response;
import br.com.outlook.langsdorf.cei4j.model.Stock;
import br.com.outlook.langsdorf.cei4j.model.User;
import br.com.outlook.langsdorf.cei4j.ws.DriverSetup;
import br.com.outlook.langsdorf.cei4j.ws.Scraping;

public class WebScrapingFactoryImpl implements WebScrapingFactory {

	@Override
	public User createFullUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setStocks(new ArrayList<Stock>());
		user.setFullname("not now");
		new Scraping(new DriverSetup(user), user).login();
		return user;
	}

	@Override
	public Response createResponse(User user) {
		return new Response(user.getFullname(), user.getStocks());
	}

}
