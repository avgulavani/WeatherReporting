package api.weather;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import api.util.RestUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import project.util.ProjectUtil;

public class SendRequest extends RestUtil {

	RequestSpecification res;
	Map<String,Float> apidatamap=new HashMap<String, Float>();
	private float temprature = 0;
	private final int  EXPECTED_RESPONSE_CODE=200;

	public void getTempratureAPI(String cityname, String unit) {

		try {
			res = given().spec(requestSpecification());
		} catch (IOException e) {
			Assert.assertFalse(true, "Request specification is wrong");
		}

		Response response = res.queryParam("q", cityname).queryParam("units", unit).get("/weather");

		String jsonString = response.asString();
	
		JsonPath js = new JsonPath(jsonString);
		Object temp = (Object)js.get("main.temp_min");
		
		Assert.assertEquals(response.getStatusCode(),EXPECTED_RESPONSE_CODE);
		Assert.assertEquals(jsonString.contains(cityname), true);
		if(temp instanceof Integer)
			temprature=((Integer) temp).floatValue();
		else
			temprature=(float) temp;
		
		System.out.println(cityname + " : temprature is " + temprature);
		Assert.assertNotNull(temprature);
		apidatamap.put(cityname, temprature);	
	}
	
	
		public void writeJsonData() {
		
		ProjectUtil.toJsonMap("Api",apidatamap);
	}
		
		public void writeTextData(String filename) {
			
			ProjectUtil.toFile(filename,apidatamap);
		}
}