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

