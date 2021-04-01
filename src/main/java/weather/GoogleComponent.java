package weather;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class GoogleComponent {
		
		protected WebDriverWait wait;
		
		public GoogleComponent(final WebDriver driver) {
			this.wait=new WebDriverWait(driver,30);
			PageFactory.initElements(driver, this);			
		}

		public abstract boolean isDisplayed();
}
