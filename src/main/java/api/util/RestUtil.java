package api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtil {

	public static RequestSpecification req;
	static JSONArray weatherList = new JSONArray();

	public static RequestSpecification requestSpecification() throws IOException {
		if (req == null) {

			String outputfilepath = System.getProperty("user.dir") + File.separator + "outdir" + File.separator
					+ "logging.txt";

			PrintStream log = new PrintStream(new FileOutputStream(outputfilepath));

			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("appid", getGlobalValue("appid")).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getGlobalValue(String key) throws IOException {

		String restapidatafile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "resources" + File.separator + "restdata.properties";

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(restapidatafile);
		prop.load(fis);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

	
	
	@SuppressWarnings("unchecked")
	public static void toJsonMap(String method, Map<String, Float> map) {

		String jsonfilepath = System.getProperty("user.dir") + File.separator + "outdir" + File.separator + "jsonmap.json";

		JSONObject weatherDetails = new JSONObject();
		JSONObject weatherObject = new JSONObject();
		
		weatherDetails.putAll(map);
		
		weatherObject.put(method, weatherDetails);
		weatherList.add(weatherObject);

	
		try (FileWriter file = new FileWriter(jsonfilepath)) {
			
			file.write(weatherList.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
