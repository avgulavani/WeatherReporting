package ui.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static final Supplier<WebDriver> chromeSupplier = () -> {	
		
		//System.getProperty("user.dir")+"\\chrome\\ChromeDriver.exe");
		System.setProperty("webdriver.chrome.driver","/Users/a.vitthal.gulavani/Desktop/Personal_Data/personal_workspace/chromedriver-2");
		return new ChromeDriver();

	};
	
	private static final Supplier<WebDriver> firefoxSupplier = () -> {
		
		System.setProperty("webdriver.gecko.driver",
				"/Users/a.vitthal.gulavani/Desktop/Personal_Data/protractor_js_framework.javascript/node_modules/protractor/node_modules/webdriver-manager/selenium/geckodriver-v0.29.0");
		return new FirefoxDriver();

	};
	

	private static final Map<String, Supplier<WebDriver>> MAP = new HashMap<>();

	static {

		MAP.put("chrome", chromeSupplier);
		MAP.put("firefox",firefoxSupplier);
	}

	public static WebDriver getDriver(String browser) {

		return MAP.get(browser).get();

	}

}
