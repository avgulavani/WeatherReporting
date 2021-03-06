package ui.weather;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ui.factory.DriverFactory;
import ui.home.page.MainPage;
import ui.weather.page.WeatherMainPage;


public class TestWeatherUI {
	
	
	private WebDriver driver;
	private MainPage mainPage;
	private WeatherMainPage weathermainpage;
	
	

	@BeforeMethod
	@BeforeTest
	@Parameters("browser")
	public void setUp(@Optional("chrome") String browser) {
		
		this.driver=DriverFactory.getDriver(browser);
		this.driver.manage().window().maximize();
		this.mainPage = new MainPage(driver);
		this.weathermainpage=new WeatherMainPage(driver);
		
	}

	@Test(dataProvider = "getData",groups={"generatedata"})
	public void testWeather(String name) {
		
		this.mainPage.launchSite();
		this.mainPage.getNavigationBar().clickSubMenu();
		this.mainPage.getNavigationBar().clickWeather();
		this.weathermainpage.getSearchWideget().enterCity(name);
		this.weathermainpage.getSearchWideget().selectCity(name);
		this.weathermainpage.getVerifyWeather().verifyCityDisplayedOnMap(name);
		this.weathermainpage.getVerifyWeather().getCitytempratureF(name);	
		Assert.assertNotNull(this.weathermainpage.getVerifyWeather().getCitytempratureF(name));
		this.weathermainpage.getVerifyWeather().verifyTempratureBoard(name);
	}
	
	

	@DataProvider
	public Object[] getData(){
		
		return new Object[] {	
				
				//"Mumbai",
				//"Srinagar",
				"Pune"
		};
	}
	
	@AfterMethod
	public void quitDriver() {
		
		this.driver.quit();
	}
	
	@AfterTest	
	public void writeData() {
		this.weathermainpage.getVerifyWeather().writeTextData("web");
	}
}
