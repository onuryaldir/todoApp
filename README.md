
# SpringBoot To-Do App with Docker

A simple to do app developed with springboot + jdk18 + maven











## Run 

3 distinct databases which are Group, User, Task.

2 of them are reserved keywords, and realized dockerization part.

So you should create database with this names.

Be careful, this database must be instance of postgres which inside of container.

default username = postgres,
 password = password

```bash 
 mvn clean package
 docker compose up --build
```
    
## Test

Postman Collection

```bash
https://www.getpostman.com/collections/cfbfd438498a7b500e6
```

  
## API usage

#### For all requests except login and signup get JWT Token



| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `Authorization` | `Bearer` | **JWT** token . |

#### Öğeyi getir



  
## Known Issues

Auto Database creation is missing. First time initiate throws database not found, but postgre service will be alive, create databases solved this issue.

An issue from hub.docker.com/maven we cannot use mvn package in Dockerfile on Windows


## Diagram

![Diagram](https://i.hizliresim.com/15n6oow.png)

  
