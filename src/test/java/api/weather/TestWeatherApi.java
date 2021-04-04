package api.weather;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWeatherApi extends SendRequest {
	
	SendRequest sendrequest=new SendRequest();
	private static String textfilepath = System.getProperty("user.dir") + File.separator + "outdir" + File.separator
			+ "apidata.txt";
	
		@Test(dataProvider = "getData")
		public void testCityTemprature(String name,String unit) {
			sendrequest.getTempratureAPI(name, unit);
	
	}
		
		@DataProvider
		public Object[][] getData(){
			
			return new Object[][]{
				
				{"Bhopal","imperial"},
				{"Mumbai","imperial"},
				{"Delhi","imperial"},
				{"Srinagar","imperial"}
			};
		}
		
		@AfterTest
		public void writeFile() {	
			sendrequest.writeTextData(textfilepath);
		}

}