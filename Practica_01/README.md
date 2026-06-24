# Practica Individual #1 - Catalogo de Suculentas

Aplicacion web CRUD desarrollada en **Spring Boot** para administrar un catalogo
de suculentas, siguiendo la arquitectura **MVC** (entidad, repositorio, servicio,
controlador y vistas Thymeleaf con Bootstrap).

## Datos del proyecto

| Elemento            | Valor                              |
|---------------------|------------------------------------|
| Proyecto            | practica01                         |
| Base de datos       | practica                           |
| Tabla principal     | suculenta                          |
| Usuario de BD       | usuario_practica / la_Clave        |
| Puerto del servidor | 91                                 |
| Nivel de log        | TRACE (con dia, hora y minuto)     |

## Requisitos

- Java 21
- MySQL (administrado con MySQL Workbench)
- Maven (incluido en NetBeans)

## Pasos para ejecutar

1. **Crear la base de datos.** Abrir MySQL Workbench y ejecutar el script:

   ```
   src/main/resources/sql/practica.sql
   ```

   Esto crea la base `practica`, la tabla `suculenta`, el usuario
   `usuario_practica` y carga 6 registros de prueba.

2. **Ejecutar la aplicacion** desde NetBeans (Run) o por linea de comandos:

   ```
   mvn spring-boot:run
   ```

3. **Abrir en el navegador:**

   - Catalogo publico:   http://localhost:91/
   - Administracion CRUD: http://localhost:91/suculenta/listado

## Arquitectura (MVC)

```
com.practica
├── Practica01Application.java      (clase principal)
├── domain/Suculenta.java           (entidad JPA)
├── repository/SuculentaRepository  (acceso a datos - JpaRepository)
├── service/SuculentaService        (logica de negocio)
└── controller/
    ├── IndexController             (pagina de inicio / catalogo)
    └── SuculentaController         (CRUD de suculentas)

resources/templates
├── fragmentos.html                 (head, menu, pie y scripts reutilizables)
├── index.html                      (catalogo dinamico)
└── suculenta/
    ├── listado.html                (tabla administrativa)
    └── modifica.html               (formulario crear / editar)
```

## Funcionalidades CRUD

- **Listar:** `/suculenta/listado` muestra todas las suculentas desde la BD.
- **Crear:** `/suculenta/nueva` formulario para registrar una suculenta.
- **Editar:** `/suculenta/modificar/{id}` modifica una suculenta existente.
- **Eliminar:** boton Eliminar en el listado.

Las imagenes se referencian mediante **URL en la nube** (campo `ruta_imagen`),
no se almacenan imagenes locales dentro del proyecto.
