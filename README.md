
# Bootcamp pr Kreitefy

Kreitefy es un proyecto cuya finalidad es la  visualización de canciones además de  realizar distintas acciones sobre las mismas a partir de una base de datos en h2.

Realizado con Spring Java para la parte del servidor y Angular en la parte del cliente.

## Puntos a realizar
- HU01 – Página de Inicio
    
    Disponible la opción de Login, título de la página y logo del producto.
- HU02 – Registro del usuario

    Si el usuario pulsa la opción de Login, se accederá a una ventana desde la que podrá autenticarse.
    Pero si el usuario no tiene cuenta, podrá acceder a la opción de registro, pulsando el enlace “regístrate” que hay en la ventana de login.
    
    Una vez que se accede a la ventana de registro el usuario podrá introducir el identificador de usuario, nombre, apellidos, contraseña, email y role (selector que solo admitirá los valores USUARIO y ADMIN). Todos los atributos serán obligatorios.
    Si el usuario pulsa el botón “Cancelar”, se volverá a la página de Inicio sin que el usuario esté registrado ni autenticado. 
    
    Si el usuario introduce los datos y pulsa el botón “Aceptar”, en el supuesto que los datos sean válidos, se dará un nuevo usuario en el sistema y se volverá a la página de inicio. En este caso, el usuario estará autenticado en el sistema, se mostrará el nombre y apellidos del usuario conectado y el botón Login ahora pasará a ser sustituido por un botón de Logout.
- HU03 – Autenticación del usuario

    Si desde la ventana de Login, el usuario introduce unas credenciales válidas y pulsa el botón “Aceptar”, se autenticará y se volverá a la página de inicio. Se volverá a la ventana de inicio, mostrará el nombre y apellidos del usuario conectado y el botón Login ahora pasará a ser sustituido por un botón de Logout. Si el usuario pulsa el botón Cancelar, se volverá a la página de inicio
- HU04 – Página de Inicio – Novedades
    
    Una vez autenticado, en la página de inicio se añadirá la tira de novedades. Para ello, se debe recuperar las 5 canciones más recientemente añadidas.
- HU05 – Página de Inicio – Filtros

    Se añadirá una opción de filtrado por el ESTILO de las canciones. En caso de tener un ESTILO seleccionado, en la tira de novedades deben visualizarse las 5 canciones más recientes de ese estilo.
- HU06 – Ficha de una canción

    Si seleccionamos una canción se deberá acceder a la ficha de la canción. Debe mostrar al menos (Imagen del álbum, Estilo, Artista, Álbum, Nombre de la canción, Duración)

    Debe permitir:

        - Valorar la canción con un sistema de estrellas (1 a 4)
        - Simular la reproducción con un botón PLAY que únicamente aumenta el número de reproducciones de la canción.
        - Se deberá guardar un historial de las reproducciones que un usuario realiza de cada canción.
- HU07 – Todas las canciones

    Añadimos un enlace que nos permita acceder a una ventana desde la que podamos ver todas las canciones de forma paginada y filtrada por los siguientes criterios (Estilo, Título, Artista y Album).
- HU08 – Página de Inicio – Más escuchadas

    En la página de inicio, añadimos la tira de más escuchadas debe recuperar las 5 canciones con más reproducciones. En caso de tener un ESTILO seleccionado deben ser las 5 canciones más escuchadas de ese estilo.
- HU09 – Página de Inicio – Para ti

    En la página de inicio, se añade la tira de los 2 ESTILOS más escuchados por ti, mostrará de todas las canciones con más de 3 estrellas de media, las 5 con mayor número de reproducciones. 
    
    Esta tira no se verá influida por tener una selección de estilo.
- HU10 – Perfil usuario

    Cuando hagamos clic en el nombre del usuario, accederemos a la ventana del perfil del usuario conectado.
    
    Esta ventana tendra dos áreas diferenciadas:

        - Mi perfil, donde se mostrarán los datos del usuario y se podrá cambiar el nombre, apellidos, email y contraseña. El id de usuario se mostrará, pero no se podrá modificar.
        - Mi historial, donde podremos ver la información histórica de todas nuestras reproducciones ordenadas de más reciente a menos reciente. Esta lista estará paginada

## Conocimientos adquiridos a destacar

- Repaso de comportamiento de tipo de usuarios a partir de variables tipo Enum.
- Configuración de seguridad del proyecto y protección de endpoints a través del framework Spring Security.
- Repaso acerca de manejo de bbdd relacionales.

## Documentación consultada

[Angular Docs](https://angular.io/)

[FontAwesome](https://fontawesome.com/)

[Bootstrap](https://getbootstrap.com/)

[ng-Bootstrap](https://ng-bootstrap.github.io/#/home)

[Data export](https://stackoverflow.com/questions/17218886/export-data-from-h2-database-into-csv)

[Formato Date](https://es.stackoverflow.com/questions/454572/fechas-en-angular-typescript)

Documentación propia del Bootcamp.


## Tecnologías empleadas

Client: Angular 16.2.0, Bootstrap, FontAwesome

Server: SpringBoot 3.2.5, Java, Mapstruct 1.5.5, Gradle, H2