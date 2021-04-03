package ui.home.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class MainPage {

	private WebDriver driver;
	private NavigationBar navigationBar;

	public MainPage(final WebDriver driver) {

		this.driver = driver;
		this.navigationBar = PageFactory.initElements(driver, NavigationBar.class);
	}

	public void launchSite() {
		this.driver.get("https://www.ndtv.com/");
	}
	
	public void navigateToWeather() {
		navigationBar.clickSubMenu();
		navigationBar.clickWeather();
		
	}
	
	
	public NavigationBar getNavigationBar() {
		return navigationBar;
	}
	
}
