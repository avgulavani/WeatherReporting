# WeatherReporting

**Information :**

1. This code has been tested successfully on the following environment 

	Os: Mac
	Chrome browser version: 89.0.4389.114
	Chrome driver version:89.0.4389.23

2. There are mainly three different xml files -
	  uitestng.xml. -> UI testng file to test UI page
	  apitestng.xml -> API testng file to test api
	  suite.xml-> which will run scripts for UI and API component and produces result in text file and then compatre

3. Main OutFile Generate:
    1. apidata.text
    2. uidata.txt

4. Test classes Names:
	  TestWeatherApi -> To test ui component
	  TestWeatherUI  -> To test api component
    TestWeatherCompareData -> to compate ui and api data

5. Test Data:

	Test data is passed using DataProvider of testng and by default api&ui tests is verifying temperature for Pune city in F.

**To Run Locally :** 
 
 In “project.properties” please mention the path for chromedriver.
