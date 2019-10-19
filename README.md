# CarSale
[![Build Status](https://travis-ci.com/java-fat-unicorn-team/CarSale.svg?branch=dev)](https://travis-ci.com/java-fat-unicorn-team/CarSale)
[![codecov](https://codecov.io/gh/java-fat-unicorn-team/CarSale/branch/master/graph/badge.svg)](https://codecov.io/gh/java-fat-unicorn-team/CarSale)
## Main topic of project
It will be a web application where you can place an advertisement for the sale of a car or find a car for yourself.\
There is DB model hierarchy [here](https://github.com/java-fat-unicorn-team/CarSale/blob/dev/docs/diagram.png).

## Technologies
Project is created with:
* Java 11
* Spring core 5.1.7
* SpringJDBC 5.1.7
* MySQL 8.0
* H2 1.4
* Maven 3.5+
* Git 2+
* JUnit 5+
	
## Run integration test
```
mvn clean verify -Pintegration_test
```
## Build
To build this project, run it locally using maven:
```
mvn clean install
```
## Deploy
* In the settings.xml file ($M2_HOME/conf/settings.xml) write the settings for access to the server. Example:
```
 <server>
     <id>TomcatServer</id>
     <username>admin</username>
     <password>admin</password>
 </server>
```
> Node: server identifier in the settings.xml file must match the identifier in pom.xml in the root of the plugin project "tomcat7-maven-plugin"

* Then run your tomcat server and deploy using tomcat maven plugin
```
 mvn tomcat7: deploy 
```
* To redeploy use code below
```
 mvn tomcat7: redeploy 
```