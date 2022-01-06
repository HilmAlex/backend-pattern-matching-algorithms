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

