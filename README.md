Spring REST Examples
======================

About Swagger: https://swagger.io/

Swagger UI is a dependency-free collection of HTML, Javascript, and CSS assets that dynamically generate beautiful documentation from a Swagger-compliant API;

https://github.com/wordnik/swagger-ui

## Build dan Run ##

Running the project : 

1. Execute mvn clean install in top level folder
2. Go to web folder, and run mvn jetty:run
3. Browse your http://localhost:9699

## Configuration ##

* Application's port : edit 'pom.xml' in top level folder, property: 'appserver.port.http'
* Context Path : edit 'pom.xml' in top level folder, property: 'appserver.deployment.context'

### Automated testing used :  ###

rest-assured
* http://code.google.com/p/rest-assured/wiki/Usage 
* http://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/ 


## For Techies, Geeks 'n Coders ##

### Framework dan Libraries ###

* Spring Framework 5.1.2.RELEASE
* Spring Security 5.1.1.RELEASE
* Springfox swagger 2.9.2
* jackson-jaxrs 2.9.9.3
* Google Guava 15.0 (important, lesser version may causing runtime error)

### Tools ###

* Build Tool : Maven 3
* Integration Test Runner : Maven Failsafe Plugin
* Functional Test : Rest-Assured, swagger-ui
