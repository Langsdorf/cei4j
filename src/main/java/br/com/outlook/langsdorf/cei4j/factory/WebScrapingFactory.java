package br.com.outlook.langsdorf.cei4j.factory;

import br.com.outlook.langsdorf.cei4j.model.Response;
import br.com.outlook.langsdorf.cei4j.model.User;

public interface WebScrapingFactory {

	User createFullUser(String user, String password);

	Response createResponse(User user);

}