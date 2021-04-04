FROM openjdk:8u191-jre-alpine3.8

#Workspace
WORKDIR /usr/share/ashish

# ADD .jar under target from host
# Into this image

ADD target/weather-test-docker.jar                weather-test-docker.jar
ADD target/weather-test-docker.jar                weather-test-docker.jar
ADD target/libs                                   libs 


# ADD suite files


ADD apitestng.xml                                  apitestng.xml
ADD uitestng.xml                                   uitestng.xml
ADD suite.xml                                      suite.xml
ADD parallelsuite.xml                              parallelsuite.xml


# in case of any other dependency like .csv / .json / /xls
# please add that as well

# BROWSER
# HUB HOST
# MODULE

ENTRYPOINT  java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

