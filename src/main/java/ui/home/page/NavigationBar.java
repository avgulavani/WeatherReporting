package ui.home.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBar extends PageAbstract {

	private WebDriver driver;

	public NavigationBar(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(id = "h_sub_menu")
	private WebElement sub_menu;

	@FindBy(linkText = "WEATHER")
	private WebElement weather;

	public void clickSubMenu() {

		try {
			this.sub_menu.click();
		} catch (Exception e) {
			this.driver.navigate().refresh();
		}
	}

	public void clickWeather() {
		this.weather.click();
	}

	@Override
	public boolean isDisplayed() {

		return this.wait.until((d) -> this.sub_menu.isDisplayed());
	}

}
