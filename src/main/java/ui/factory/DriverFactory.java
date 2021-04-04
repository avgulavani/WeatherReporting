package ui.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
		
		ChromeOptions chromeOptions = new ChromeOptions();
		DesiredCapabilities cap = DesiredCapabilities.chrome();

		try {
			System.setProperty("webdriver.chrome.driver",getGlobalValue("chromebrowserpath"));
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
		String host = "localhost";
		
			if (System.getProperty("HUB_HOST") != null) {
				System.out.println("In Remote driver class");
				host = System.getProperty("HUB_HOST");
				String completeURL = "http://" + host + ":4444/wd/hub";
				
				try {
					driver = new RemoteWebDriver(new URL(completeURL), cap);
				} catch (MalformedURLException e) {
				    
					e.printStackTrace();
				}
				return driver = new ChromeDriver(chromeOptions);
			}
			else
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
	
	private static String getGlobalValue(String key) throws IOException {

		String propertyfile = System.getProperty("user.dir") + File.separator + "project.properties";

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(propertyfile);
		prop.load(fis);
		return prop.getProperty(key);

	}


}
