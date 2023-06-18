package Modelo;

import java.io.Serializable;
import java.util.Objects;

public class Competidor implements Serializable {

    private String nombre;
    private String info;

    public Competidor(String nombre){
        this.nombre = nombre;
        info = "no disponible";
    }

    public Competidor(String nombre, String info){
        this.nombre = nombre;
        this.info = info;
    }

    public String getNombre() {
        return nombre;
    }

    public String getInfo() {
        return info;
    }


    @Override
    public String toString() {
        return "Modelo.Competidor:\n" +
                "nombre: " + nombre + ", info: " + info + "\n";
    }
}
