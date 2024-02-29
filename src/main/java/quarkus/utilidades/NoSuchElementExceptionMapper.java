package quarkus.utilidades;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.NoSuchElementException;
@Provider
public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException> {
    public static record NoSuchElementMessage(String message, String detail){

    }
    @Override                                                   // aqui tratamos de otra forma las exception.
    public Response toResponse(NoSuchElementException exception){
        var error = new NoSuchElementMessage(exception.getMessage(),null);
        return Response.status(404).entity(error).build();

    }
}
