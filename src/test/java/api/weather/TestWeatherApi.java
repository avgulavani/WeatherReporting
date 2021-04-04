package api.weather;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWeatherApi extends SendRequest {
	
	SendRequest sendrequest=new SendRequest();
	
		@Test(dataProvider = "getData",groups={"generatedata"})
		public void testCityTemprature(String name,String unit) {
			sendrequest.getTempratureAPI(name, unit);
	
	}
		
		@DataProvider
		public Object[][] getData(){
			
			return new Object[][]{
				
			//	{"Bhopal","imperial"}
			//	{"Mumbai","imperial"},
			//	{"Delhi","imperial"},
			//	{"Srinagar","imperial"},
				{"Pune","imperial"}
			};
		}
		
		@AfterTest
		public void writeFile() {	
			sendrequest.writeTextData("api");
		}

}