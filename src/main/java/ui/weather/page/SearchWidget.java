package ui.weather.page;

import static org.testng.AssertJUnit.assertFalse;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.common.util.concurrent.Uninterruptibles;

import ui.home.page.PageAbstract;

public class SearchWidget extends PageAbstract {

	private WebDriver driver;
	private WebElement cityname;

	public SearchWidget(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	@FindBy(id = "searchBox")
	private WebElement searchbox;

	@FindBy(id = "messages")
	private WebElement allcitylist;

	private final String citynamexpath = "//input[@id=\'{name}\']";

	public void enterCity(String cityname) {

		try {
			this.searchbox.clear();
			for (char ch : cityname.toCharArray()) {
				Uninterruptibles.sleepUninterruptibly(30, TimeUnit.MILLISECONDS);
				this.searchbox.sendKeys(String.valueOf(ch));
			}
		} catch (NoSuchElementException e) {
			Assert.assertFalse(true, "error while entering city");
		}

	}

	public void selectCity(String name) {

		String cityxpath = citynamexpath.replace("{name}", name);

		try {
			if (!this.driver.findElement(By.xpath(cityxpath)).isSelected()) {
				this.driver.findElement(By.xpath(cityxpath)).click();
			}
		} catch (NoSuchElementException e) {
			Assert.assertFalse(true, "Entered city is not listed, try another city, please.");
		}
	}

	@Override
	public boolean isDisplayed() {

		return this.wait.until((d) -> this.searchbox.isDisplayed());
	}

}
