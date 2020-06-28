##Employee Management Api##

#>This application is simple demonstration of rest web service where a user can perform operations like create,read,update,delete information of an employee.

#Technologies used
#Language:-Java 8
#Database:-H2(In memory database)
#Testing:-Junit-Mockito

#There are so much things covered in application for learning purpose
# Things covered in this application
#1)Rest Api Crud Operations
#2)Global Exception Handling mechanism in Rest Api
#3)Creating custom validator for validating fields
#3)Implementing Swagger which provides kind of UI to perform rest operations and also documents rest request and response format.
#4)Mocking rest api with mockito for  testing components of application
#5)Learn how to configure a In-memory database.
#6)Spring test slicing:-Optimized way of creating test slices and testing each layer of application creating beans only needed for particular layer.For Controller use @WebmvcTest and for Repository layer @DataJpaTest.
#7)Using Lombok annotations to reduce boiler plate code.
#8)Lombok annotations used for loggers and implemented builder design pattern.
#9)Using Spring conversion service:-As it is a small application used spring conversion instead of depending on third party jars like dozzer ,model mapper  service to convert entity to model and vice versa as per requirement.
#10)Spring boot actuator for checking status of application



#This application is also deployed to Pivotal Cloud Foundry


#Steps to run this application locally
#1)First run RestDemoApplication.java as spring boot application and check console  for tomcat started at port 8085.
#2)Open browser enter http://localhost:8085/swagger-ui.html.Hitting this url swagger will get launched
#3)In another tab enter http://localhost:8085/h2-console in order to view in memory h2 database.Login to db using username,pwd and db url given in application.properties file.
#4)Perform operations from through endpoints given in swagger ui.
#5)Verify results through Swagger and also check h2 database for same.




