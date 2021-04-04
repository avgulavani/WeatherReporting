package ui.weather.page;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import project.util.ProjectUtil;
import ui.home.page.PageAbstract;

public class VerifyWeather extends PageAbstract {

	private WebDriver driver;
	private WebElement cityname;
	private WebElement citytempF;
	private static Map<String, Float> uidatamap=new HashMap<String, Float>();

	public VerifyWeather(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	@FindBy(id = "map_canvas")
	private WebElement map_canvas;

	@FindBy(xpath = "//div[@class='leaflet-popup-content-wrapper']")
	private WebElement tempPopUp;

	private final String citytextXpath = "//div[@class='cityText' and contains(text(),\'{name}\')]";
	private final String citytempratureF = "//div[@class='cityText' and contains(text(),\'{name}\')]/../div/span[2]";

	public void verifyCityDisplayedOnMap(String name) {

		String cityxpath = citytextXpath.replace("{name}", name);

		try {
			this.driver.findElement(By.xpath(cityxpath)).isDisplayed();
		} catch (NoSuchElementException e) {
			Assert.assertFalse(true, "Entered city is not displayed on canvas map");
		}
	}

	public float getCitytempratureF(String name) {

		String citytempratureFxpath = citytempratureF.replace("{name}", name);
		String tempF = null;
		float convertedFloattemp;
		try {
			tempF = driver.findElement(By.xpath(citytempratureFxpath)).getText();
		} catch (NoSuchElementException e) {
			Assert.assertFalse(true, "Temprature is not displayed on canvas map");
		}
		tempF=ProjectUtil.formatValue(tempF, 2);
		System.out.println("temprature from ui " + tempF);
		convertedFloattemp=ProjectUtil.convertToFloat(tempF);
		uidatamap.put(name, convertedFloattemp);
		return convertedFloattemp;
	}

	public void verifyTempratureBoard(String name) {

		String cityxpath = citytextXpath.replace("{name}", name);
		this.driver.findElement(By.xpath(cityxpath)).click();
		try {
			tempPopUp.isDisplayed();
		} catch (NoSuchElementException e) {
			Assert.assertFalse(true, "Temprature pop up board is not displayed on canvas map");
		}
		 catch (Exception e) {
				Assert.assertFalse(true, "Temprature pop up board is not displayed on canvas map");
			}
	}

	@Override
	public boolean isDisplayed() {

		return this.wait.until((d) -> this.map_canvas.isDisplayed());
	}
	
	public void writeTextData(String method) {
		
		ProjectUtil.toFile(method,uidatamap);
	}

}
