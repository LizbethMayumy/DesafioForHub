# 📚 Desafío ForoHub

Proyecto desarrollado con **Spring Boot** como parte del desafío **ForoHub** de Alura.  
El sistema implementa un **foro académico** donde los usuarios pueden registrarse, autenticarse y gestionar tópicos de discusión.

---

## 🚀 Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA / Hibernate**
- **Jakarta Validation**
- **MySQL / H2 Database**
- **Maven**

---

## 📌 Funcionalidades principales

### 🔑 Autenticación
- Registro de usuarios con **rol/perfil**.
- Login con generación de **JWT**.
- Seguridad con **Spring Security**.

### 📝 Gestión de Tópicos
- **Crear** un nuevo tópico (`POST /topicos`)
- **Listar** todos los tópicos (`GET /topicos`)
- **Detalle** de un tópico específico (`GET /topicos/{id}`)
- **Actualizar** un tópico (`PUT /topicos/{id}`)
- **Eliminar** un tópico (`DELETE /topicos/{id}`)

### 👤 Usuarios
- Relación **Usuario ↔ Perfil** para roles.
- Validaciones de correo único y campos obligatorios.

---

## 🔑 Endpoints principales

| Recurso       | Método | Ruta              | Descripción                       | Rol           |
|---------------|--------|-----------------|-----------------------------------|---------------|
| Autenticación | POST   | /auth/login      | Iniciar sesión y obtener JWT      | Público       |
| Autenticación | POST   | /auth/register   | Registrar usuario                 | Público       |
| Cursos        | GET    | /cursos          | Listar cursos                     | Usuario/Admin |
| Cursos        | POST   | /cursos          | Registrar nuevo curso             | Admin         |
| Tópicos       | GET    | /topicos         | Listar todos los tópicos          | Usuario/Admin |
| Tópicos       | POST   | /topicos         | Crear un nuevo tópico             | Usuario/Admin |
| Tópicos       | PUT    | /topicos         | Actualizar un tópico existente    | Usuario/Admin |
| Tópicos       | DELETE | /topicos/{id}    | Eliminar un tópico                | Usuario/Admin |
| Respuestas    | GET    | /respuestas      | Listar respuestas                 | Usuario/Admin |
| Respuestas    | POST   | /respuestas      | Registrar respuesta a un tópico   | Usuario/Admin |

---

## 📄 Estructura de DTOs

- **Usuarios:** `DatosRegistroUsuario`, `DatosLogin`, `DatosPerfil`  
- **Cursos:** `DatosRegistroCurso`, `DatosDetalleCurso`  
- **Tópicos:** `DatosRegistroTopico`, `DatosDetalleTopico`, `DatosActualizarTopico`, `DatosTopico`  
- **Respuestas:** `DatosRegistroRespuesta`, `DatosDetalleRespuesta`  

---

## 🛡 Seguridad

- Roles: `ROLE_admin`, `ROLE_user`  
- JWT para autenticación  
- Endpoints protegidos con `@PreAuthorize`

## 👩‍💼 Autor
- Lizbeth Mayumy Mamani
