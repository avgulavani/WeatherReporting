package ui.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	
	public static WebDriver driver;

	private static final Supplier<WebDriver> chromeSupplier = () -> {	
		
		if (driver == null)
		{
		
			/* local run */
			try {
				driver = createDriver();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
			//	driver = setupDriver();
			
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
	
	private static String getGlobalValue(String key) throws IOException {

		String propertyfile = System.getProperty("user.dir") + File.separator + "project.properties";

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(propertyfile);
		prop.load(fis);
		return prop.getProperty(key);

	}
	
	public static WebDriver setupDriver() {

		String host = "localhost";
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		if (System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
			capabilities = DesiredCapabilities.firefox();

		}
		if (System.getProperty("HUB_HOST") != null) {
			host = System.getProperty("HUB_HOST");

		}
		
		String completeURL = "http://" + host + ":4444/wd/hub";
		
		try {
			driver = new RemoteWebDriver(new URL(completeURL), capabilities);
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}
		return driver;
	}
	
	
	private static WebDriver createDriver() throws IOException {

		ChromeOptions chromeOptions = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver",getGlobalValue("chromebrowserpath"));
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("--always-authorize-plugins");
		chromeOptions.setAcceptInsecureCerts(false);
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--disable-extensions");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--window-size=1600,1200");

		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Dimension d = new Dimension(1500, 2500);
		driver.manage().window().setSize(d);
		return driver;
	}



}
