package compare.data;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import compare.files.CompareWeatherData;

public class TestWeatherCompareData {
	

	private static final String apifile = System.getProperty("user.dir") + File.separator + "apidata.txt";
    private static final String uifile = System.getProperty("user.dir")  + File.separator+ "uidata.txt";

	@Test(groups={"comparedata"})
	public void validateData() {
		
				Assert.assertTrue(CompareWeatherData.compareWeatherData(apifile, uifile));
			
	}

}
