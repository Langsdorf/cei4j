package br.com.outlook.langsdorf.cei4j.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import br.com.outlook.langsdorf.cei4j.model.Stock;
import br.com.outlook.langsdorf.cei4j.model.User;
import br.com.outlook.langsdorf.cei4j.ws.config.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Scraping {

	private DriverSetup driver;
	private User user;

	public void login() {
		WebDriver chrome = getDriver().getDriver();
		chrome.get(Configuration.MAIN_URL);
		WebElement loginButton = getDriver().getDriverWait()
				.until(ExpectedConditions.visibilityOfElementLocated(By.name(Configuration.LOGIN_BUTTON_PATH)));
		WebElement userInput = getDriver().getDriverWait()
				.until(ExpectedConditions.visibilityOfElementLocated(By.name(Configuration.NAME_INPUT_PATH)));
		WebElement passwordInput = getDriver().getDriverWait()
				.until(ExpectedConditions.visibilityOfElementLocated(By.name(Configuration.PASSOWORD_INPUT_PATH)));
		userInput.sendKeys(getUser().getUsername());
		passwordInput.sendKeys(getUser().getPassword());
		loginButton.click();
		getFullName();
	}

	public void getFullName() {
		WebElement fullname = getDriver().getDriverWait()
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(Configuration.FULLNAME_ID_PATH)));
		getUser().setFullname(fullname.getText());
		getStocks();
	}

	public void getStocks() {
		HashMap<String, Stock> stockList = new HashMap<>();

		WebDriver chrome = getDriver().getDriver();
		chrome.get(Configuration.STOCKS_URL);
		WebElement button = getDriver().getDriverWait()
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(Configuration.STOCK_VIEW_BUTTON_PATH)));
		button.click();

		List<WebElement> tables = getDriver().getDriverWait()
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Configuration.TABLES_PATH)));
		for (int i = 0; i < (tables.size() - 1); i++) {
			List<WebElement> stocksName = getDriver().getDriver()
					.findElements(By.xpath(Configuration.STOCKS_PATH.replace("$i$", Integer.toString(i))));
			for (WebElement webElement : stocksName) {
				List<WebElement> names = checkoutList(webElement.findElements(By.xpath("//td[1]")));
				List<WebElement> codes = checkoutList(webElement.findElements(By.xpath("//td[3]")));
				List<WebElement> currentValues = checkoutList(webElement.findElements(By.xpath("//td[8]")));
				List<WebElement> lastPrices = checkoutList(webElement.findElements(By.xpath("//td[5]")));
				List<WebElement> quantities = checkoutList(webElement.findElements(By.xpath("//td[6]")));
				for (int i2 = 0; i2 < names.size(); i2++) {
					String name = names.get(i2).getText();
					if (checkout(name)) {
						String code = codes.get(i2).getText();
						double currentValue = Double.valueOf(currentValues.get(i2).getText().replace(",", "."));
						double lastPrice = Double.valueOf(lastPrices.get(i2).getText().replace(",", "."));
						int quantity = Integer.valueOf(quantities.get(i2).getText());
						Stock stock = new Stock();
						stock.setCode(code);
						stock.setCurrentValue(currentValue);
						stock.setLastPrice(lastPrice);
						stock.setName(name);
						stock.setQuantity(quantity);
						stockList.put(code, stock);
					}
				}
			}
		}
		chrome.get(Configuration.STOCKS_TRADES_URL);
		Select select = new Select(getDriver().getDriverWait().until(
				ExpectedConditions.visibilityOfElementLocated(By.name(Configuration.STOCKS_TRADES_SELECT_PATH))));
		int options = select.getOptions().size();
		for (int i = 1; i < options; i++) {
			chrome.get(Configuration.STOCKS_TRADES_URL);
			select = new Select(getDriver().getDriverWait().until(
					ExpectedConditions.visibilityOfElementLocated(By.name(Configuration.STOCKS_TRADES_SELECT_PATH))));
			select.selectByIndex(i);
			WebElement confirmButton = getDriver().getDriverWait().until(
					ExpectedConditions.visibilityOfElementLocated(By.id(Configuration.STOCKS_TRADES_BUTTON_PATH)));
			confirmButton.click();
			List<WebElement> table = getDriver().getDriverWait().until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(Configuration.STOCKS_TRADES_TABLE_PATH)));
			for (WebElement webElement : table) {
				List<WebElement> codes = webElement.findElements(By.xpath("//td[1]"));
				List<WebElement> buyAveragePrices = webElement.findElements(By.xpath("//td[5]"));
				List<WebElement> buyQuantities = webElement.findElements(By.xpath("//td[3]"));
				for (int i2 = 0; i2 < codes.size(); i2++) {
					String code = codes.get(i2).getText();
					if (code.endsWith("F"))
						code = code.substring(0, code.lastIndexOf("F"));
					if (stockList.containsKey(code)) {
						Stock stock = stockList.get(code);
						double ap = Double.valueOf(buyAveragePrices.get(i2).getText().replace(",", "."));
						int quantity = Integer.valueOf(buyQuantities.get(i2).getText());
						double appliedValue = quantity * ap;
						stock.setAppliedValue(appliedValue);
						stock.setAveragePrice(ap);
						stock.setProfit();
						stockList.replace(code, stock);
					}
				}
			}
		}
		getUser().setStocks(stockList.values().stream().collect(Collectors.toList()));
		quit();
	}

	public void quit() {
		getDriver().getDriver().quit();
	}

	private boolean checkout(String text) {
		if (text.isBlank())
			return false;
		if (text.isEmpty())
			return false;
		if (text.startsWith("Total da"))
			return false;
		if (text.startsWith("R$"))
			return false;
		return true;
	}

	private List<WebElement> checkoutList(List<WebElement> list) {
		List<WebElement> newList = new ArrayList<>();
		for (WebElement webElement : list) {
			if (checkout(webElement.getText())) {
				newList.add(webElement);
			}
		}
		return newList;
	}

}
