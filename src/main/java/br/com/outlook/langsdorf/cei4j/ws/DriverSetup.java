package br.com.outlook.langsdorf.cei4j.ws;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.outlook.langsdorf.cei4j.model.User;
import br.com.outlook.langsdorf.cei4j.ws.config.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverSetup {

	private WebDriver driver;
	private ChromeOptions chromeOptions;
	private WebDriverWait driverWait;

	private User user;

	public DriverSetup(User u) {
		setUser(u);

		System.setProperty("webdriver.chrome.driver", Configuration.CHROMEDRIVER_PATH);
		setChromeOptions(new ChromeOptions());
		addArguments();
		setDriver(new ChromeDriver(getChromeOptions()));
		setDriverWait(new WebDriverWait(getDriver(), Configuration.TIMEOUT));
	}

	private void addArguments() {
		getChromeOptions().addArguments("--headless");
		getChromeOptions().addArguments("--disable-gpu");
		getChromeOptions().addArguments("--no-sandbox");
	}

}
