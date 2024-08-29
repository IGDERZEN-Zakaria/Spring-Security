# Spring-Security

##  Prerequisites

In order to work with Spring Security Tutorial

- JDK 11.0.24
- Maven 3.8.8

## Getting Started

After Creating the project with Spring Initialize, we choose these parameters

- Java 11
- Spring Web

### Adding Student Package

We Add Student RestController and model inside Student Package to the project 

![student.png](images%2Fstudent.png)

### testing the Endpoint

http://localhost:8080/api/v1/students/1

![student_api.png](images%2Fstudent_api.png)


## Setting Spring Security 

### Adding Spring Security dependency to the pom.xml file

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### re-running the application 

![springinitpass.png](images%2Fspringinitpass.png)

### refreshing the browser

![springinit.png](images%2Fspringinit.png)

### Credentials :

UserName : user 

Passowrd : c462e42e-8eb4-4ee1-a41e-5a6b03331850

### Result

![student_api.png](images%2Fstudent_api.png)


