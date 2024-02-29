package quarkus.temperature;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/temperatures")
public class TemperaturesResource {


    private TemperaturesService temperatures;
    @Inject
    public TemperaturesResource(TemperaturesService temperatures){
        this.temperatures = temperatures;
    }

    //En esta lección muestro cómo hacer un endpoint dentro de nuestro recurso que tiene la anotación POST para indicar que se debe ejecutar
    // cuando la petición usa el verbo HTTP POST, y cómo podemos leer el payload mediante Jackson para convertirlo directamente a un objeto, como una clase.
    // Quarkus también se va a ocupar de transformar el JSON de entrada en una instancia de la clase que le proporcionemos como parámetro.
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature nueva(Temperature temp){                         // me devuelve temp por que le entra como parametro (Temperature temp). puede entrar. clase, objetos, atributos.
        temperatures.addTemperature(temp);                              //aqui guardamos los nuevos parametros en lista temperatura.
        return (temp);                                                  //aqui devolvemos la temperatura q se a incorporado.
    }

    @GET                                                                //en este endpoind solicitamos la lista de datos y los devuelve en la URL en json
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperature> list(){
        return temperatures.obtenerTemperature();                       //aqui devuelve la lista de valores.
    }


    @GET
    @Path("/maxima")
    @Produces(MediaType.TEXT_PLAIN)
    public Response maxima(){
       if (temperatures.isEmpty()){                                                             // aqui estamos pidiendo permiso antes de devolver un error.con .isEmpty().
            return Response.status(404).entity("no hay temperaturas").build();               // esto lo que hace es decir q hay un error pero mas delicado.
                                                                                               // es decir q no tiene parametros para mostrar
        }else {
            int temperatureMaxima = temperatures.maxima();
            return Response.ok(Integer.toString(temperatureMaxima)).build();
        }

    }

    @GET                                                                                        //aqui otra forma de tratar exceptiones.
    @Path("{ciudad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature sacar(@PathParam("ciudad") String ciudad){
        return temperatures.sacarTemperature(ciudad).get();
    }
}
