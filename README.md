# 🚀 Aplicación YourCSStats 🚀
Aplicación para registrar y guardar los resultados y estadísticas de tus partidas del videojuego CS2.

## ⚙️ Versión 2.1 (MVVM)

En esta versión, no ha habido un cambio con respecto a las funciones de la aplicación, pero si sobre la estructura, y la forma de renderizar las diferentes ventanas y pantallas de la misma. 

## 📂 Estructura
Se ha creado una nueva estructura de los archivos de la aplicación.
```plaintext
/java/com/example/version1_1
│
├── LoginActivity.kt              Pantalla de inicio de sesión
├── RegisterActivity.kt           Pantalla de registro de usuarios
│
├── /data                         Capa de datos de la aplicación
│   ├── /datasource               Fuentes de datos
│   │   └── Partidas.kt           Gestión de partidas
│   ├── /models                   Modelos de datos
│   │   └── Partida.kt            Modelo de una partida
│   ├── /repository               Repositorio de datos
│   │   └── PartidaRepository.kt  Lógica de acceso a datos
│   ├── /service                  Servicios de datos
│   │   └── PartidaService.kt     Servicios relacionados con partidas
│
├── /domain                       Capa de dominio (lógica de negocio)
│   ├── /models                   Modelos de dominio
│   │   ├── Partida.kt            Modelo de una partida en la lógica de negocio
│   │   ├── PartidaData.kt        Datos adicionales de partida
│   ├── /repository               Interfaces de acceso a datos
│   │   └── PartidaRepositoryInterface.kt  Interfaz del repositorio de partidas
│   ├── /usecase                  Casos de uso
│   │   ├── DeletePartidaUseCase.kt  Eliminar una partida
│   │   ├── EditPartidaUseCase.kt    Editar una partida
│   │   ├── GetPartidasUseCase.kt    Obtener partidas
│   │   └── InsertPartidaUseCase.kt  Insertar una partida
│
├── /ui                           Capa de presentación
│   ├── /adapter                  Adaptadores para la vista
│   │   ├── AdapterPartida.kt      Adaptador para partidas
│   │   ├── ViewHolderPartida.kt   ViewHolder para partidas
│   ├── /modelview                ViewModels
│   │   └── PartidasViewModel.kt   ViewModel de partidas
│   ├── /views                    Vistas de la aplicación
│   │   ├── MainActivity.kt        Pantalla principal
│   │   ├── /fragment              Fragmentos de la UI
│   │   │   ├── AddPartidaDialogFragment.kt  Diálogo para agregar partida
│   │   │   ├── EditPartidaDialogFragment.kt Diálogo para editar partida
│   │   │   ├── MainFragment.kt   Fragmento principal
│   │   │   ├── PartidasFragment.kt Fragmento de lista de partidas
```

## 📂 Descripción de cada capa

### 1️⃣ **Data (`/data/`)**
Esta capa es responsable de manejar la fuente de datos de la aplicación. Contiene modelos de datos, fuentes de datos locales o remotas(actualmente, solo locales), y repositorios que actúan como intermediarios entre la capa de datos y la capa de dominio.

### 2️⃣ **Domain (`/domain/`)**
La capa de dominio contiene la lógica de negocio central de la aplicación. Define modelos específicos del dominio, interfaces de repositorio y casos de uso que representan acciones clave dentro de la app.

### 3️⃣ **UI (`/ui/`)**
Esta capa maneja la presentación de la aplicación. Incluye adaptadores, `ViewModels` que gestionan los datos para la vista, y las propias vistas representadas por actividades y fragmentos.

---

## 🔄 Flujo de Datos en MVVM
```plaintext
Vista (Activity/Fragment) <---> ViewModel <---> Repository <---> Base de Datos/API
```

## Versión 4.1: RETROFIT 💾

# API de Gestión de Partidas y Autenticación de Usuarios

Una API para gestionar partidas de un videojuego y la autenticación/registro de usuarios.

---

## Procedimientos para la Gestión de Partidas

### ENDPOINTS

| Método | Ruta                                   | Descripción                                                                                                                                                                                |
|--------|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /partida                               | Obtener todas las partidas. Devuelve una lista en formato JSON.                                                                                                                           |
| GET    | /partida/{nombrePartida}               | Obtener los detalles de una partida identificada por su nombre.                                                                                                                            |
| POST   | /partida                               | Insertar una nueva partida. Recibe un JSON que se mapea al modelo **Partida**.                                                                                                             |
| PATCH  | /partida/{nombrePartida}               | Actualizar una partida existente. Recibe un JSON que se mapea al modelo **Partida** y actualiza la partida identificada por `nombrePartida`.                                                 |
| DELETE | /partida/{nombrePartida}               | Eliminar una partida identificada por su nombre.                                                                                                                                           |

### Gestión de Partidas

Todos los endpoints del grupo `/partida` requieren autenticación mediante **JWT**. El token debe enviarse en la cabecera **Authorization** con el prefijo `Bearer`.

#### Obtener partidas (GET /partida)

- **Autenticación:** Se valida el token en la cabecera.
- **Descripción:**  
  Devuelve una lista completa de partidas en formato JSON.
- **Respuestas:**  
  - **200 OK:** Lista de partidas.
  - **401 Unauthorized:** Token inválido o ausente.

#### Obtener partida por nombre (GET /partida/{nombrePartida})

- **Autenticación:** Se valida el token.
- **Parámetro de ruta:**  
  - `nombrePartida`: Nombre de la partida a buscar.
- **Respuestas:**  
  - **200 OK:** Detalles de la partida.
  - **404 Not Found:** Si la partida no existe.
  - **401 Unauthorized:** Token inválido o ausente.

#### Insertar una nueva partida (POST /partida)

- **Autenticación:** Se valida el token.
- **Cuerpo de la solicitud:**  
  - JSON representando el modelo **Partida**.
- **Respuestas:**  
  - **201 Created:** Partida insertada correctamente.
  - **409 Conflict:** Si ya existe una partida con el mismo identificador.
  - **400 Bad Request:** Error en el formato de la solicitud o datos insuficientes.

#### Actualizar una partida (PATCH /partida/{nombrePartida})

- **Autenticación:** Se valida el token.
- **Parámetro de ruta:**  
  - `nombrePartida`: Nombre de la partida a actualizar.
- **Cuerpo de la solicitud:**  
  - JSON con los datos actualizados mapeados al modelo **Partida**.
- **Respuestas:**  
  - **200 OK:** Actualización realizada con éxito.
  - **404 Not Found:** Si la partida no existe.
  - **400 Bad Request:** Error en el formato de la solicitud o datos inválidos.

#### Eliminar una partida (DELETE /partida/{nombrePartida})

- **Autenticación:** Se valida el token.
- **Parámetro de ruta:**  
  - `nombrePartida`: Nombre de la partida a eliminar.
- **Respuestas:**  
  - **200 OK:** Eliminación realizada correctamente.
  - **404 Not Found:** Si la partida no existe.
  - **401 Unauthorized:** Token inválido o ausente.

---

## Procedimientos para la Autenticación y Registro de Usuarios

### ENDPOINTS

| Método | Ruta      | Descripción                                                                                                                    |
|--------|-----------|--------------------------------------------------------------------------------------------------------------------------------|
| POST   | /auth     | Iniciar sesión. Recibe credenciales y, si son correctas, retorna un objeto **AuthResponse** con token y datos del usuario.        |
| POST   | /register | Registrar un nuevo usuario. Recibe un JSON que se mapea al modelo **User** y, en caso de éxito, registra el usuario en el sistema. |

### Gestión de Usuarios

#### Iniciar sesión (POST /auth)

- **Cuerpo de la solicitud:**  
  Se debe enviar un JSON mapeado al modelo **LoginRequest**, que contiene:
  - `dni`: Documento de identidad del usuario.
  - `password`: Contraseña del usuario.
- **Proceso:**  
  1. Se recibe la solicitud de inicio de sesión.
  2. Se valida la autenticación con las credenciales proporcionadas.
  3. Si la autenticación es exitosa, se retorna un objeto **AuthResponse** que incluye el token JWT.
- **Respuestas:**  
  - **200 OK:** Autenticación exitosa.
  - **401 Unauthorized:** Credenciales incorrectas.
  - **400 Bad Request:** Error en el formato de la solicitud.

#### Registrar un nuevo usuario (POST /register)

- **Cuerpo de la solicitud:**  
  Se debe enviar un JSON mapeado al modelo **User**.
- **Proceso:**  
  1. Se recibe la solicitud de registro.
  2. Se procesa el registro del nuevo usuario.
  3. Si el registro es exitoso, se responde con **201 Created**.
- **Respuestas:**  
  - **201 Created:** Usuario registrado correctamente.
  - **409 Conflict:** Usuario ya existe o hay conflicto en los datos.
  - **400 Bad Request:** Error en el formato de la solicitud o datos insuficientes.

---

Este documento sirve como referencia para implementar o consumir la API en una aplicación Android utilizando Retrofit.
