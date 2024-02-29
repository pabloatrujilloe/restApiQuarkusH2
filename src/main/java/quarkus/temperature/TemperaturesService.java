package quarkus.temperature;

import jakarta.enterprise.context.ApplicationScoped;
import quarkus.temperature.Temperature;

import java.util.*;


// aqui veremos el patrón Service Object, para desacoplar la lógica de negocio del controlador HTTP.
// Mediante inyección de depednencia instanciaremos automáticamente ese servicio en el controlador.
@ApplicationScoped //conectamos con controller
public class TemperaturesService {
    private List<Temperature> valores = new ArrayList<>();              //aqui llegan todo los post nuevos que se van agregando a la lista.

    public void addTemperature(Temperature temperature){
        valores.add(temperature);
    }

    public List<Temperature> obtenerTemperature() {                     //la funcion de este metodo es devolver una lista de valores.y no se pueden modificar.
        return Collections.unmodifiableList(valores);
    }

    public boolean isEmpty(){                                           //se utiliza para verificar si la coleccion de valores esta vacia o no.
        return valores.isEmpty();
    }

    public int maxima(){                                                //calcula el promedio maximo de la lista de valores.
        return valores.stream().mapToInt(Temperature::getMaxima).max().getAsInt();

    }

    public  Optional<Temperature> sacarTemperature(String ciudad){
        return valores.stream()
                .filter(temperature -> temperature.getCiudad().equals(ciudad))
                .findAny();
    }


}
