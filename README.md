## Servicios

El proyecto está compuesto por dos microservicios independientes que se comunican de forma asíncrona a través de RabbitMQ.

**client-service** — gestiona clientes y personas. Expone su API en el puerto `8081`.

**account-service** — gestiona cuentas, movimientos y reportes. Escucha eventos del client-service para mantener una réplica local de clientes. Expone su API en el puerto `8082`.

## Tecnologías

- Java 21
- Spring Boot 4.0.3 (Web MVC, JPA, Validation, AMQP)
- PostgreSQL 17
- RabbitMQ 3
- Maven
- Docker

## Requisitos previos

- Docker Desktop instalado y en ejecución
- Los puertos 5432, 5672, 15672, 8081 y 8082 disponibles

## Ejecución con Docker

Desde la raíz del proyecto:

```bash
docker-compose up -d
```

Esto levanta la base de datos, RabbitMQ y ambos microservicios. La primera vez tarda un poco más porque descarga las imágenes base.

Para ver los logs de un servicio en particular:

```bash
docker logs tcs-client-service
docker logs tcs-account-service
```

Para detener todo:

```bash
docker-compose down
```

## Script de base de datos

El archivo `BaseDatos.sql` contiene el esquema completo y datos de prueba. Si necesitas ejecutarlo manualmente contra una base de datos existente:

```bash
psql -h localhost -U tcs_user -d client_db -f BaseDatos.sql
```

Las tablas también se crean automáticamente al levantar los servicios gracias a la configuración de JPA.

## Colección Postman

En la carpeta `postman/` se encuentra la colección lista para importar en Postman. Contiene todos los endpoints organizados para seguir el flujo completo: crear clientes, crear cuentas, registrar movimientos y consultar reportes.
