# wchallenger


#Bases del ejercicio
El objetivo es desarrollar una API que pueda integrarse a un servicio externo,
de forma que pueda utilizar la información allí almacenada. Además se tiene que
ampliar el scope del desarrollo agregando funcionalidad nueva que persista
información nueva el sistema relacionada con los datos de la API externa. Para
realizar esto se puede utilizar cualquier lenguaje de programación y con el
framework de desarrollo. Un requisito que se debe cumplir es que el código debe
estar alojado en un repositorio en Github o Gitlab.
Problema de negocio
Se tiene que consumir información del siguiente servicio externo que cuenta
con la información de usuarios, sus álbumes y sus fotos; además de sus posts y los
comentarios de otros usuarios sobre ellos: https://jsonplaceholder.typicode.com/
De la información del servicio se tiene que poder acceder a través de nuestra API a:
#UserServiceController.java
1. Los usuarios.

#PhotoServiceController.java 
2. Las fotos. 

#AlbumServiceController.java
3. Los álbumes del sistema y de cada usuario.

#PhotoServiceController.java path /users/{userId}/photos
4. Plus: Las fotos de un usuario.  

De esta forma la API va a poder concentrar esta información para luego poder
extenderla a una nueva funcionalidad, la cual consiste en implementar una gestión
de permisos básica a cada albúm de fotos (lectura y escritura) para compartir
álbumes entre los usuarios de la plataforma. De esta manera se debe registrar qué
usuarios tienen acceso a álbumes que no son propios y los permisos de dicho
usuario para ese álbum. Para persistir y consumir esta información nueva el sistema
debe permitir:

#PermissionServiceContoller.java

1. Registrar un álbum compartido con un usuario y sus permisos. 
2. Cambiar los permisos de un usuario para un álbum determinado.
3. Traer todos los usuarios que tienen un permiso determinado respecto a un
álbum específico.
En cuanto a los comentarios, se espera que la aplicación pueda traerlos del
servicio externo brindando la posibilidad de filtrar por el campo “name” o por el
usuario que realizó dicho comentario.


#Rutas del ejercicio
http://localhost:8080/

GET /albums

GET /albums/

GET /albums/{id}

GET /albums?userId=#{userId}


GET /comments

GET /comments/

GET /comments/{id}

GET /comments?postId={postId}

GET /comments?name={name}


GET  /permissions

POST /permissions

PUT  /permissions/{id}

GET  /permissions?albumId={albumId}


GET /photos

GET /photos/

GET /photos/{id}

GET /users/{userId}/photos

GET /photos?albumId={albumId}


GET /users/

GET /users/{id}



#Tecnologías

Se desarrollo un MicroServicio con Spring-boot usando los modulos Data con JPA agregando la base de datos H2 en memoria para facilidad de la prueba.
Se puede validar los servicios rest ya sea por un cliente rest o ingresando en la dirección http://localhost:8080/swagger-ui.html contiene una implementación de la Api Swagger para la documetación de servicios rest y pruebas.

#Instrucciones para despliegue

Sobre el path del respositorio


$ mvn install

$ java -jar target/wchallenger-0.0.1-SNAPSHOT.jar 
