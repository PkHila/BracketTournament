package Modelo;

import java.io.Serializable;

/**
 * Esta clase contiene la información de un Competidor básico, con nombre y un texto descriptivo. Es serializable, por tanto, las clases hereden de ella
 */
public class Competidor implements Serializable {

    private String nombre;
    private String info;

    /**
     * Instancia un Competidor vacío
     */
    public Competidor() {
    }

    /**
     * Instancia un Competidor con su nombre pero info por defecto
     * @param nombre nombre descriptivo
     */
    public Competidor(String nombre){
        this.nombre = nombre;
        info = "no disponible";
    }

    /**
     * Instancia un Competidor con su nombre y su texto descriptivo
     * @param nombre nombre descriptivo
     * @param info informacion adicional
     */
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
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        String s = "";
        s = s.concat("Competidor:\n" + "nombre: " + nombre + ", info: " + info);
        return s;
    }
}
