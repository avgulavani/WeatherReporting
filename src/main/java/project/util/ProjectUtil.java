package project.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class ProjectUtil {

	static JSONArray weatherList = new JSONArray();
	private static String jsonfilepath = System.getProperty("user.dir") + File.separator  +  "apidata.json";
	private static String apitextfilepath = System.getProperty("user.dir") + File.separator +  "apidata.txt";
	private static String uitextfilepath = System.getProperty("user.dir") + File.separator   + "uidata.txt";
	static float temprature;
	private final static int BEGIN_INDEX = 0;
	private static final float TEMPRATURE_THRSHOLD = 1;
	private static  String filename;
	

	@SuppressWarnings("unchecked")
	public static void toJsonMap(String method, Map<String, Float> map) {

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

	public static float convertToFloat(Object temprature) {

		if (temprature instanceof Integer)
			temprature = ((float) temprature);
		else if (temprature instanceof String)
			temprature = Float.parseFloat((String) temprature);
		else
			temprature = (float) temprature;

		return (float) temprature;
	}

	public static String formatValue(String value, int requiredindex) {
		return value.substring(BEGIN_INDEX, requiredindex);
	}

	public static List<String> readTextFile(String filename) {
		

		Path path = Paths.get(filename);
		List<String> ls = null;
	
					try {
						ls = Files.readAllLines(path);
						if(ls.isEmpty())
							throw new Exception ("File is empty");
					} catch (Exception ex) {
						Assert.assertFalse(true, "Probabley file has not data");
					}
					return ls;
						
	}

	public static void toFile(String method, Map<String, Float> weathermap) {
		
		if(method=="api")
			filename=apitextfilepath;
		else
			filename=uitextfilepath;
			
		File file = new File(filename);
		

		BufferedWriter bf = null;

		try {

			bf = new BufferedWriter(new FileWriter(file));

			// iterate map entries
			for (Entry<String, Float> entry : weathermap.entrySet()) {

				// put key and value separated by a colon
				bf.write(entry.getKey() + ":" + entry.getValue());
				bf.newLine();
			}
			bf.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static float compareFloatValue(Object object, Object object2) {

		float diff = convertToFloat(object) - convertToFloat(object2);
		return diff;

	}

	public static Map<Object, Object> convertListtoMap(List<String> ls) {

		Map<Object, Object> map = ls.stream().map(str -> str.split(":"))
				.collect(Collectors.toMap(str -> str[0], str -> str[1]));

		return map;
	}
	
	public static List<String> converttoList(List<String> ls) {

		return Stream.of(ls) // Stream<Stream<String>>
				.flatMap(Collection::stream) // Stream<String>
				.flatMap(Pattern.compile(":")::splitAsStream) // Stream<String>
				.collect(Collectors.toList()); // List<String>
	}
	
	public static boolean checkWeatherData(Map<Object, Object> m1, Map<Object, Object> m2) {
		boolean result = false;
		for (Object key : m1.keySet()) {
			if (m2.containsKey(key)) {

				float difference = ProjectUtil.compareFloatValue(m1.get(key), m2.get(key));
	
				if (Math.abs(difference) <= TEMPRATURE_THRSHOLD)
					result = true;
				else
					return false;
			}
		}
		System.out.println("** result from util**" + result);
		return result;
	}
	


        public static boolean areEqual(Map<Object, Object> apimap, Map<Object, Object> uimap) {
        	
        	return apimap.entrySet()
            .stream()
            .allMatch(e->e.getValue().equals(uimap.get(e.getValue())));
        }
	}

