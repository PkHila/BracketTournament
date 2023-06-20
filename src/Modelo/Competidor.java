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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Competidor competidor && competidor.getNombre().equals(this.nombre);
    }

    @Override
    public String toString() {
        return "Competidor:\n" +
                "nombre: " + nombre + ", info: " + info + "\n";
    }
}
