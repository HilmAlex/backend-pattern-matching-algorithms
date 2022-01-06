# BACKEND PATTERN MATCHING ALGORITHMS

API to test Pattern Matching Algorithms Performance - DataStructures and Algorithms Project

Es una api creada en Java utilizando el framework Spring Boot, consta de 3 endpoints:
 - brute-force
 - kmp
 - boyer
Cada endpoint devuelve el tiempo y las ocurrencias de un texto y patrón, el algortimo a utilizar está dado por el endpoint. El texto y el patrón son enviados en la url.

Para probar la api seguir las siguientes instrucciones:

## Pre Requisitos:
 - Java 11
 - Maven

## Para correr el servidor:
Por defecto el servidor corre en el puerto 8080, si desea cambiarlo lo puede hacer en el archivo src/main/resources/application-dev.properties

 - mvn clean install
 - ./mvnw spring-boot:run

## En caso de no querer instalar el servidor, puede realizar peticiones a la api desplegada en heroku: https://backend-pattern-matching.herokuapp.com/
### Ejemplo:
    https://backend-pattern-matching.herokuapp.com/api/boyer-moore?text=hello&pattern=world
    
    La respuesta será un JSON con la información de las ocurrencias encontradas y el tiempo de ejecución
    ![image](https://user-images.githubusercontent.com/71728367/148459479-13e8c75d-7a20-4549-ac17-fe18437adecb.png)

