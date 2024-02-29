package quarkus.temperature;

import java.util.Objects;

public class Temperature {

    private String  ciudad;
    private int minima;
    private int maxima;
                                                //para crear esto boton derrecho mouse generate y tienes estas opciones.
    public Temperature() {                                            // constructor vacio
    }

    public Temperature(String ciudad, int minima, int maxima) {       //constructor con campos
        this.ciudad = ciudad;
        this.minima = minima;
        this.maxima = maxima;
    }
                                                                      // aqui tenemos los getter y setter
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }
                                                                                       //equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Temperature that))
            return false;
        return getMinima() == that.getMinima() && getMaxima() == that.getMaxima() && Objects.equals(getCiudad(), that.getCiudad());
    }


                                                                                        //hashCode
    @Override
    public int hashCode() {
        return Objects.hash(getCiudad(), getMinima(), getMaxima());
    }
                                                                                      // aqui toString
    @Override
    public String toString() {
        return "Temperature{" +
                "ciudad='" + ciudad + '\'' +
                ", minima=" + minima +
                ", maxima=" + maxima +
                '}';
    }
}
