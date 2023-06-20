package Modelo;

import java.util.Objects;

public class Competidor{

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
    public String toString() {
        return "Modelo.Competidor:\n" +
                "nombre: " + nombre + ", info: " + info + "\n";
    }
}