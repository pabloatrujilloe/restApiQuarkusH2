package quarkus.ejemplo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.util.Optional;



@Path("/hello")
public class EcoResource {


// metodo para pasar un mensaje a la URL y me la devuelva en la pagina con  @QueryParam.
//http://localhost:8080/hello?message=buenas%20tardes%20a%20amigos.
//si ponemos algo despues de = nos devolvera la URL o puerto 8080.
//pasar parametro a nuestros endpoind por la misma URL.
   @GET
   public String hello(@QueryParam("message") String message){
        return Optional.ofNullable(message).orElse("no se que decirte !!");


   }

    @GET
    @Path("/{nombre}") //cuando ponemos / lo q coloquemos se devolvera en la URL
    public String hellos(@PathParam("nombre")String nombre){
        return "HOLA, " + nombre.toUpperCase(); // siempre ira hola + lo q pongas en el String de texto. en la URL con el .toUpperCase();te trae todo en mayuscula
    }


    //Cómo usar PathParam y QueryParam para especificar en la URL ciertas variables y que su contenido esté disponible desde las funciones de nuestros endpoints.
    // De este modo podemos hacer endpoints cuyo comportamiento dependa de la información que le enviemos como parámetro.
    // En este vídeo vemos estas dos formas de enviar información, que será complementado en el futuro con el envío de cargas completas
    // compatibles con otro tipo de verbos HTTP.
}
