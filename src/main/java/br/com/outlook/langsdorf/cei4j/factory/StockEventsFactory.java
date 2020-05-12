package br.com.outlook.langsdorf.cei4j.factory;

import br.com.outlook.langsdorf.cei4j.model.Stock;

public interface StockEventsFactory {

	void registerPurchase(Stock stock, int quantity, double value);

	void registerSell(Stock stock, int quantity, double value);

}
