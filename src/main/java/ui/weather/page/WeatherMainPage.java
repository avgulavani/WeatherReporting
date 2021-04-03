package ui.weather.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class WeatherMainPage {
	
	private WebDriver driver;
	private SearchWidget searchWidget;
	private VerifyWeather verifyWeather;
	
	public WeatherMainPage(final WebDriver driver) {
		
		this.driver=driver;
		this.searchWidget=PageFactory.initElements(driver, SearchWidget.class);
		this.verifyWeather=PageFactory.initElements(driver, VerifyWeather.class);
	}
	
	
	public SearchWidget getSearchWideget() {
		return searchWidget;
	}
	
	public VerifyWeather getVerifyWeather() {
		return verifyWeather;
	}

}
