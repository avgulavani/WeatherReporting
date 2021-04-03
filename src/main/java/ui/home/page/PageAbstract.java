package ui.home.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageAbstract {
		
		protected WebDriverWait wait;
		
		public PageAbstract(final WebDriver driver) {
			this.wait=new WebDriverWait(driver,60);
			PageFactory.initElements(driver, this);			
		}

		public abstract boolean isDisplayed();
}
