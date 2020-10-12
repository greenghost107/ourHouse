#   OurHouse

House managing application, contains both Backend And Fronend

## Features
    * Registration
    * Authorization
    * House registration
    * House joining
    * Creation of grocery list
    * addition of grocerys


## Prerequisites
java 8
mysql 8+
node

## Setup
within application.properties, change your-db-name,your-user,your-password

```
spring.datasource.url=jdbc:mysql://localhost:3306/<your-db-name>?useSSL=false
spring.datasource.username=<your-user>
spring.datasource.password=<your-password>
```

run the backend as spring boot application
run cmd within ..\ourHouse\web\ourHouseAngularApp 
```
ng serve
```

## Basic usage
To be able to access the server you will need to authenticate
```
Post Request - http://<your-ip>:8080/register
```
you should send a json
{
"username":"yourName"
"password":"yourPassword"
}

```
Post Request - http://<your-ip>:8080/authenticate
```
which will return a token that can be used to access the api-s

