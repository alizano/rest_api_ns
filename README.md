# rest_api_ns

#App Create using spring boot

To deploy only need the application jar and JDK 8+
Then run `java -jar rest-api-ns-0.0.1-SNAPSHOT.jar`

#Hal Implementation

You can use HAL by default going to http://localhost:8080/ 

#Api Examples

Default endpoints:

 * http://localhost:8080/categories
 * http://localhost:8080/categories/1
 * http://localhost:8080/process


Documentation available on Postman with examples to use the api

https://documenter.getpostman.com/view/6221751/S1a1bUTS?version=latest 


#Monitoring

* Actuator is implemented to check app status.

 * http://localhost:8080/actuator
 * http://localhost:8080/actuator/health
 * http://localhost:8080/actuator/info


