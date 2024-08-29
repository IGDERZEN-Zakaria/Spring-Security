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

**http://localhost:8080/api/v1/students/1**

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

****Result****

![student_api.png](images%2Fstudent_api.png)

****Logout****

**http://localhost:8080/logout**

![springinitlogout.png](images%2Fspringinitlogout.png)

****inspect****

![springinitinspect.png](images%2Fspringinitinspect.png)

this is an example of form based authentication


### Basic Auth

![basicAuth.png](images%2FbasicAuth.png)

#### we add the security package, and we implement the application securityConfig

![packagebasic.png](images%2Fpackagebasic.png)

****Result****

![basicAuthLog.png](images%2FbasicAuthLog.png)

for basic auth the username & password are sent on every single request so we cannot use Logout

****Using Postman to test the application****

![basicAuthPostman.png](images%2FbasicAuthPostman.png)

![basicauthdecode.png](images%2Fbasicauthdecode.png)

if we decode the selected text after **Basic** , we find 

```
# username:password

user:df088746-2018-40cb-a5cf-66273a1335e2
```

### Ant Matchers 

we use ant matchers to white-list some uri with security

by adding index.html inside static folder and whitelisting the route of index we don't need to
specify the username and the password

![index.png](images%2Findex.png)




















