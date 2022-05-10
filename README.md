# Antra REST API Demo
## INTRODUCTION
### Main application
* Technologies used - Spring Boot, Spring Data JPA, Swagger
* Embedded Server - H2
* Start the application by running [UserRestStarter class](src/main/java/com/antra/UserRestStarter.java)
* Configuration file - [application.properties](src/main/resources/application.properties)
* data.sql and schema.sql will be automatically populated during startup.
* Swagger generated API doc can be accessed from [localhost:port/swagger-ui.html](localhost:8009/swagger-ui.html)


### Test
* Technologies used - Spring Test, JUnit, REST Assured
* Run *mvn install* or *mvn jacoco:report* will generate test coverage report in _target/site/jacoco/index.html_


### Todo
* For Simplicity, no security. We may add a basic http authentication later.
* Jenkins integration / Email Notification  
* Added Trigger for Jenkins

## Contributor
[Manas](mailto:manas.gampa@antra.com)

TestCICD
