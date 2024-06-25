# Desafío Agilesoft

A continuación se presenta la solución (diseño e implementación) del desafío Agilesoft correspondiente al desarrollo de una API Rest en Java Springboot. El enunciado del desafío se encuentra el siguiente [LINK](/docs/Prueba-desarrollo-backend.pdf)

# Diseño

Se trata de dar solución al problema con un enfoque de "design-first". El problema comprende el registro de los usuairos, el inicio de sesión y un CRUD de tareas. Para el diseño de la solución se comienza definiendo las entidades del negocio (dominio), encontrándose: Usuario y Tarea:

![Diagrama de componentes](docs/images/challenge-component.png)

Según el enunciado, se pueden establecer algunos atributos iniciales y las relaciones que pueden tener las entidad de negocio. A continuación, se presenta el esquema inicial de entidad-relación con dichos atributos y que será implementado posteriormente:

![Diagrama entidad relación](docs/images/challenge-entity-relation.png)

La aplicación se diseñará considerando una arquitectura limpia (hexagonal), aplicando los principios SOLID y, si es necesario, aplicar patrones de diseño.

## Casos de uso

Según el enunciado se tienen 7 casos de uso a implementar para su funcionamiento:

1. Inscribir un usuarios
2. Login
3. Obtener datos usuario a partir de sesión
4. Obtener listado de tareas del usuario y su estado
5. Agregar tarea asociada al usuario
6. Marcar como resuelta una tarea de un usuario
7. Eliminar una tarea de un usuario

Si bien se implementarán todos los casos de uso, solo se diseñará y documentará como ejemplo el caso de uso: *Agregar tarea asociada al usuario*; para la implementación de los otros casos de usos se seguirá el mismo patrón

## Diagrama de secuencia

A continuación, se presenta el diagrama de secuencia del caso de uso *Agregar tarea asociada al usuario*. En el diagrama se puede apreciar (de forma simplificada) las interacciones entre las capas de la arquitectura limpia, así como las reglas de aplicación y negocio:

![Diagrama de secuencia](docs/images/challenge-sequence.png)

## Diseño detallado

A continuación se presenta el diseño detallado del caso de uso *Agregar tarea asociada al usuario*. En el diagrama se puede apreciar los componentes que interactuan: controllers, casos de uso, repositories en las distintas capas de la arquitectura limpia:

![Diagrama de componentes](docs/images/challenge-detail-design.png)

## Open API

Se ha generado un archivo .yml con la documentación inicial de la API considerando el análisis que se ha realizado hasta ahora. El archivo se encuentra [AQUI](docs/swagger/challenge-swagger.yml)

Se ha subido la documentación de la API a la siguiente URL:

[https://app.swaggerhub.com/apis/FAQ_CODES/Agilesoft/1.0.0](https://app.swaggerhub.com/apis/FAQ_CODES/Agilesoft/1.0.0)

Imagen de referencia de la API:

![https://app.swaggerhub.com/apis/FAQ_CODES/Agilesoft/1.0.0](docs/images/challenge-swagger.png)


# Implementación

## Estructura de carpetas

Para la estructura de carpeta se utiliza arquitectura hexagonal + vertical slicing, quedando de la siguiente manera (ejemplo):

```
- src /
    users /
      application /
      domain /
      infrastructure/
    tasks /
      application /
      domain /
      infrastruture /
...
```

## Librerías

Para la creación del proyecto se utilizará principalmente:

```
Spring Data JPA SQL
Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.

H2 Database SQL
Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser based console application.

Lombok DEVELOPER TOOLS
Java annotation library which helps to reduce boilerplate code.

Spring Boot DevTools DEVELOPER TOOLS
Provides fast application restarts, LiveReload, and configurations for enhanced development experience.

Docker Compose Support DEVELOPER TOOLS
Provides docker compose support for enhanced development experience.

Spring Web WEB
Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.

Spring HATEOAS WEB
Eases the creation of RESTful APIs that follow the HATEOAS principle when working with Spring / Spring MVC.

Spring Security SECURITY
Highly customizable authentication and access-control framework for Spring applications.

Spring Session WEB
Provides an API and implementations for managing user session information.
```

## Docker

Para el proyecto se utilizará el contenedor Docker para su simplicidad de uso. Este contenedor contine la aplicación y puede ser obtenido con la siguiente sentencia:

```
docker pull faqcodes/challenge-agilesoft
docker run -p 8080:8080 faqcodes/challenge-agilesoft
```

Si se requiere construir y ejecutar se utilizan las siguientes sentencias:

```
docker build -t challenge-agilesoft .
docker run -p 8080:8080 challenge-agilesoft

```

## Uso de la Aplicación

Los siguientes comandos cURL se utilizan para el uso de la aplicación:

1. Registro de un usuario (signup):

```
curl --location 'localhost:8080/api/users/signup' \
--header 'Content-Type: application/json' \
--data '{
    "name": "felipe",
    "username": "fquiroz",
    "password": "fquiroz"
}'
```
RESULTADO:
```
{"code":"SUCCESS","message":"El usuario se ha creado satisfactoriamente","errors":null,"data":{"name":"felipe","username":"fquiroz","links":[]}}
```

2. Inicio de sesión de un usuario (signin). El inicio de sesión retornará un TOKEN que se debe utilizar para las peticiones subsiguientes

```
curl --location 'localhost:8080/api/users/signin' \
--header 'Content-Type: application/json' \
--data '{
    "username": "fquiroz",
    "password": "fquiroz"
}'
```
RESULTADO:
```
{"code":"SUCCESS","message":"Se ha iniciado sesión satisfactoriamente","errors":null,"data":{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcXVpcm96IiwiaWF0IjoxNzE5Mjk4MzI0LCJleHAiOjE3MTkyOTg2MjR9.ayg79KUbjdCo7qDih_BQ8kJhgBcfw-K6YQ7DbITdX6o","links":[]}}
```

3. Crear una tarea:

```
curl --location 'localhost:8080/api/tasks' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcXVpcm96IiwiaWF0IjoxNzE5Mjk4MzI0LCJleHAiOjE3MTkyOTg2MjR9.ayg79KUbjdCo7qDih_BQ8kJhgBcfw-K6YQ7DbITdX6o' \
--data '{
    "name": "tarea 1",
    "description": "descripcion 1"
}'
```
RESULTADO:
```
{"code":"SUCCESS","message":"La tarea se ha creado satisfactoriamente","errors":null,"data":{"taskId":"f559b7bf-dc89-4402-bfac-34fe35855b55","name":"tarea 1","description":"descripcion 1","createdAt":"2024-06-25T06:53:36.198867940","updatedAt":"2024-06-25T06:53:36.198905947","status":false}}
```
4. Obtener la lista de tareas:

```
curl --location 'localhost:8080/api/tasks' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcXVpcm96IiwiaWF0IjoxNzE5Mjk4MzI0LCJleHAiOjE3MTkyOTg2MjR9.ayg79KUbjdCo7qDih_BQ8kJhgBcfw-K6YQ7DbITdX6o'
```
RESULTADO:
```
{"code":"SUCCESS","message":"Las tareas se han obtenido satisfactoriamente","errors":null,"data":{"tasks":[{"taskId":"f559b7bf-dc89-4402-bfac-34fe35855b55","name":"tarea 1","description":"descripcion 1","createdAt":"2024-06-25T06:53:36.198868","updatedAt":"2024-06-25T06:53:36.198906","status":false}]}}
```
5. Actualizar una tarea. Aquí se marca una tarea como realizada (status = true)

```
curl --location --request PUT 'localhost:8080/api/tasks' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcXVpcm96IiwiaWF0IjoxNzE5Mjk4MzI0LCJleHAiOjE3MTkyOTg2MjR9.ayg79KUbjdCo7qDih_BQ8kJhgBcfw-K6YQ7DbITdX6o' \
--data '{
    "taskId": "f559b7bf-dc89-4402-bfac-34fe35855b55",
    "status": true
}'
```
RESULTADO:
```
{"code":"SUCCESS","message":"La tarea se ha actualizado satisfactoriamente","errors":null,"data":{"taskId":"f559b7bf-dc89-4402-bfac-34fe35855b55","name":"tarea 1","description":"descripcion 1","createdAt":"2024-06-25T06:53:36.198868","updatedAt":"2024-06-25T06:55:34.351765528","status":true}}
```
