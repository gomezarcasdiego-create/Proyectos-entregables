# Biblioteca Decroly

Este proyecto sirve para gestionar los libros y las categorías de la biblioteca. De momento los datos se guardan en memoria, así que se reinician cuando se apaga la aplicación.

## Qué necesito

- Java 25, que es la versión configurada en el proyecto.
- El proyecto ya incluye Maven Wrapper.

## Cómo arrancarlo

Abre PowerShell en la carpeta del proyecto y ejecuta:

```powershell
.\mvnw.cmd spring-boot:run
```

También se puede arrancar desde IntelliJ ejecutando `BibliotecaDecrolyApplication`.

La aplicación usa esta dirección: `http://localhost:8080`.

## Rutas

### Categorías

| Método | Ruta | Para qué sirve |
|---|---|---|
| GET | `/api/categorias` | Ver todas las categorías |
| GET | `/api/categorias/{id}` | Buscar una categoría |
| POST | `/api/categorias` | Crear una categoría |
| PUT | `/api/categorias/{id}` | Modificar una categoría |
| DELETE | `/api/categorias/{id}` | Borrar una categoría |

### Libros

| Método | Ruta | Para qué sirve |
|---|---|---|
| GET | `/api/libros` | Ver todos los libros |
| GET | `/api/libros?autor=texto` | Buscar libros por autor |
| GET | `/api/libros/disponibles` | Ver los libros disponibles |
| GET | `/api/libros/{id}` | Buscar un libro |
| POST | `/api/libros` | Crear un libro |
| PUT | `/api/libros/{id}` | Modificar un libro |
| DELETE | `/api/libros/{id}` | Borrar un libro |

### Información de la biblioteca

| Método | Ruta | Para qué sirve |
|---|---|---|
| GET | `/api/biblioteca/info` | Ver el nombre y los datos de préstamo |

## Configuración

Estos valores están en `src/main/resources/application.properties`:

- Nombre: `Biblioteca Decroly`
- Máximo de préstamos: `3`
- Días de préstamo: `15`

## Swagger y Postman

- Swagger: `http://localhost:8080/swagger-ui.html`
- OpenAPI: `http://localhost:8080/v3/api-docs`
- La colección de Postman está en `postman/`. La variable `baseUrl` vale `http://localhost:8080`.
