# Forohub 📚

_Forohub_ es una API REST desarrollada como parte del **challenge ForoHub de Alura LATAM**, que funciona como backend de un foro para cursos.  
Permite registrar cursos y gestionar tópicos (temas de discusión) relacionados, con autenticación mediante JWT.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security + JWT
- MySQL para persistencia
- Flyway para migraciones
- Postman para testear endpoints

---

## ⚙️ Cómo ejecutar el proyecto

1. Clona el repositorio:

   git clone https://github.com/Jorgelizarraga94/ForoHubAlura.git

2. Configura la base de datos:

   - Asegurate de tener MySQL corriendo.
   - Crea una base de datos llamada `forohub`.
   - Edita el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:

     spring.datasource.url=jdbc:mysql://localhost/forohub  
     spring.datasource.username=${DB_USERNAME} 
     spring.datasource.password=${DB_PASSWORD}

3. Ejecuta el proyecto con Maven:

   ./mvnw spring-boot:run

4. Flyway creará automáticamente las tablas a partir de los scripts ubicados en `resources/db.migration`.
5. Cree un login y contraseña del mismo mediante sql
6. Testea los endpoints usando Postman.

---

## 📝 Endpoints disponibles

### 📌 Cursos

- **Agregar curso**  
  Método: POST  
  Endpoint: `/curso`  
  Body de ejemplo:

      {
        "nombre": "Spring Boot",
        "categoria": "PROGRAMACION"
      }

---

### 🔐 Login

- **Autenticación**  
  Método: POST  
  Endpoint: `/login`  
  Body de ejemplo:

      {
        "login": "usuario",
        "contrasenia": "password"
      }

  La respuesta incluirá un token JWT que deberás usar en el header `Authorization`:

      Authorization: Bearer <token>

---

### 💬 Tópicos

- **Crear tópico**  
  Método: POST  
  Endpoint: `/topico`  
  Body de ejemplo:

      {
        "titulo": "¿Cómo usar Spring Boot?",
        "mensaje": "¿Alguien tiene un ejemplo simple?",
        "autor": "Pepe Argento",
        "curso": "Spring Boot"
      }

- **Listar tópicos**  
  Método: GET  
  Endpoint: `/topico`

- **Buscar tópico por ID**  
  Método: GET  
  Endpoint: `/topico/{id}`

- **Actualizar tópico**  
  Método: PUT  
  Endpoint: `/topico/{id}`  
  Body de ejemplo:

      {
        "titulo": "Título actualizado",
        "mensaje": "Mensaje actualizado",
        "autor": "Homero Simpson"
      }

- **Eliminar tópico**  
  Método: DELETE  
  Endpoint: `/topico/{id}`

---

## 🚀 Ejemplo de uso con Postman

1. Hacer login:
   - POST `/login`  
     Body:

         {
           "login": "usuario",
           "contrasenia": "password"
         }

2. Copiar el token que retorna y agregarlo a los headers en las siguientes requests:

      Authorization: Bearer <token>

3. Probar los endpoints protegidos (crear, actualizar, eliminar tópicos, etc).

---

## ✅ Funcionalidades implementadas

- Autenticación con JWT
- ABM (CRUD) de tópicos
- Validación de datos
- Migraciones automáticas con Flyway
- Documentación y testing mediante Postman

---

## ✍️ Autor

- Jorge Lizarraga - [GitHub](https://github.com/Jorgelizarraga94)

