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

## Users,Role & Authorities

![userRole.png](images%2FuserRole.png)

### Creating In Memory Users

we use the interface `UserDetailsService` inside the ApplicationSecurityConfig Class to create 3 
in memory users to which we assign a different role for each 

![encodePass.png](images%2FencodePass.png)

### Result 

when login with these credentials we get 

![PassEncode2.png](images%2FPassEncode2.png)

We need to define a password encoder , by implementing `PasswordEncoder` interface and adding 
an encoder in our case we choose `BCryptPasswordEncoder` 

### Result

![PassEncode3.png](images%2FPassEncode3.png)

We have to  add the `PasswordEncoder` By injecting it to the ApplicationSecurityConfig to be able to use it

### Roles Definition 

<img src="images/PassEncode4.png" width="540" height="300"></img>

<img src="images/PassEncode5.png" width="300" height="300"></img>

trying to separate Api for each role 

```
// By adding this to Security Config we enforce only users with role STUDENT to access the route "/api/**"

.antMatchers("/api/**").hasRole(STUDENT.name())

```

### Result

trying to access the route with Linda that has the Role ADMIN

![PassEncode6.png](images%2FPassEncode6.png)
















