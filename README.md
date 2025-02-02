
## Versión 1.4 Navegación

En esta versión hemos añadido la navegación de la aplicación.

Se ha implementado una Toolbar, o barra de herramientas, donde tenenmos varios botones, los cuales son para hacer un cierre de sesión/LogOut, y otro para desplegar el menu de navegación.

Este menú es un NavigatiónView, que está compuesto por dos partes.

La primera es la cabecéra, donde tenemos una imagen de perfil, el nombre del usuario y el correo.

Debajo de la cabecera encontramos los diferentes menús por los que podemos navegar en nuestra aplicación. 

Tenemos el botón de Inicio, donde, en esta versión, tendremos un simple bienvenida a la aplicación, junto a un botón que nos llevará a la pantalla del listado.

También tenemos un botón de Perfil, que también sirve para ir al listado del usuario. Este será el botón principal para navergar asta dicha pantalla, ya que la pantalla de bienvenida será cambiada en proximas versiones.

Por último tenemmos un botón de Configuración, que actualmente no funciona, ya que esa parte todavía está en desarrollo.


Estos serían los cambios principales y visibles realizados en esta versión.

Ademas, para poder implementar la navegación, he cambiado la forma en la que estába estructurada la aplicación. Hemos pasado de una unica Activity para mostrar todo, ha varios fragmentos, los cuales son cada una de las pantallas de la navegación, los cuales se cargan o renderizan dentro de la Main Activity.
