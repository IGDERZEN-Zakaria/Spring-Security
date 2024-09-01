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

```xml
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

****Result (200 Sucess :+1: )****

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

```java
// By adding this to Security Config we enforce only users with role STUDENT to access the route "/api/**"

.antMatchers("/api/**").hasRole(STUDENT.name())

```

### Result (403 :-1: )

trying to access the route with Linda that has the Role ADMIN

![PassEncode6.png](images%2FPassEncode6.png)

## Permission Based Authentication

We Implement the StudentManagementController by adding the methods of REST API : 

**(GET / POST / PUT / DELETE)**

![Permiss.png](images%2FPermiss.png)

When we try to **POST / PUT / DELETE** Spring Security will automatically protect our App

To resolve this we need to **disable CSRF** `(Cross-site request forgery)` in the security Config

```java
http.csrf().disable()
```

### Result (200 Sucess :+1: )

![Permiss2.png](images%2FPermiss2.png)

![Permiss3.png](images%2FPermiss3.png)


### To Implement Permission based authentication there are 2 ways :

- Using AntMatchers in Application Security Config
- Using Annotations in the methods 


### 1- Using AntMatchers in Application Security Config

if we go deep into User Class we will find 

![Permiss4.png](images%2FPermiss4.png)

We will find that thr roles are GrantedAuthorities preceeded only with "ROLE_"
and that is the only difference with Permissions 

If we go deep inside UserDetails interface, we will find that it uses only GrantedAuthorities

![Permiss5.png](images%2FPermiss5.png)

### Conclusion

If We want to define roles based on GrantedAuthorities we have to do it by ourselves 
inside the Role Enum as bellow 

```java
public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions  = getPermissions().stream()
                                               .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                                               .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
}
```

After changing the Application Sercurity Config 

![Permiss6.png](images%2FPermiss6.png)


### Result 

- annasmith can only access [localhost:8080/api/v1/students/{StudentId}]()
- tom can only acess GET [localhost:8080/management/api/v1/students]()
- linda can do everything in [localhost:8080/management/api/v1/students]()

### Important:

**AntMatchers order is crucial and can influence the application ,
Rememeber to always structure your antMatchers from Specific cases before the more generic cases**


### 2- Using Annotations on method level

We comment the antMatchers Part 

![Permiss7.png](images%2FPermiss7.png)

and instead we use 

![Permiss8.png](images%2FPermiss8.png)

#### Example 1:

```java
@GetMapping
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
public List<Student> getAllStudents() {
    System.out.println("getAllStudents");
    return STUDENTS;
}
```

#### Example 2:

```java
@PostMapping
@PreAuthorize("hasAuthority('student:write')")
public void registerNewStudent(@RequestBody Student student) {
    System.out.println("registerNewStudent");
    System.out.println(student);
}
```

**We also have to add this annotation on the `ApplicationSecurityConfig` or the`StudentManagementController`
( preferably `ApplicationSecurityConfig`) to inform our configuration that we are using @PreAuthorize
for permission or role based authentication**

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)
```

### Result (Success :+1:)

## Cross Site Request Forgery

![csrf.png](images%2Fcsrf.png)

Spring Security by default activate the csrf protection as bellow

![csrf2.png](images%2Fcsrf2.png)

### When to use CSRF Protection :

CSRF Protection should be used for any request that could be processed by a browser
by normal users ,Although if we are creating a service that is used by non-browser 
clients, we would likely disable the CSRF Protection


- CSRF protection is enabled by default in Spring Security. However, if you have explicitly 
disabled it, or it's not correctly configured, the token will not be generated.

- Use CookieCsrfTokenRepository.withHttpOnlyFalse() to ensure that the CSRF token is stored in 
a cookie that can be accessed by the client-side.

- we get the **XSRF-TOKEN** in a `GET` to be used in `POST` / `PUT` / `DELETE` 


```java
// Enable the CSRF token to be set as a cookie
 http   
     .csrf()
     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
     .and()
     .authorizeRequests()
```

#### Result (without XSRF-TOKEN :-1: ) 

![csrf4.png](images%2Fcsrf4.png)

#### Result (with XSRF-TOKEN :1+: ) 

if we use the **XSRF-TOKEN** in the header of our request 

![csrf5.png](images%2Fcsrf5.png)

### withHttpOnlyFalse() method

```java
//means that the coookie will be inaccessible to the client side scripts 
// (ex: javascript)

public static CookieCsrfTokenRepository withHttpOnlyFalse() {
    CookieCsrfTokenRepository result = new CookieCsrfTokenRepository();
    result.setCookieHttpOnly(false);
    return result;
}
```

Emphasize on `CookieCsrfTokenRepository` and `CsrfFilter`

**We will be working with services with Postman , which requires us to disable the CSRF**













