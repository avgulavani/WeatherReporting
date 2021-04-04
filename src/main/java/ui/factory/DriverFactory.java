package ui.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	
	private static WebDriver driver;

	private static final Supplier<WebDriver> chromeSupplier = () -> {	
		
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver","/Users/a.vitthal.gulavani/Desktop/Personal_Data/personal_workspace/chromedriver-2");
		String host = "localhost";
		
			if (System.getProperty("HUB_HOST") != null) {
				host = System.getProperty("HUB_HOST");
			}
			else
				return new ChromeDriver();
			
			String completeURL = "http://" + host + ":4444/wd/hub";
			
			try {
				driver = new RemoteWebDriver(new URL(completeURL), capabilities);
			} catch (MalformedURLException e) {
			    
				e.printStackTrace();
			}
		
			return driver;

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
