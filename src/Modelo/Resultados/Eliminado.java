package Modelo.Resultados;

import Modelo.Competidor;

public class Eliminado extends Competidor {
    private Competidor eliminadoPor;
    private String enRonda;


    public Eliminado(String nombre, String info, Competidor eliminadoPor, String enRonda) {
        super(nombre, info);
        this.eliminadoPor = eliminadoPor;
        this.enRonda = enRonda;
    }

    public Competidor getEliminadoPor() {
        return eliminadoPor;
    }

    public String getEnRonda() {
        return enRonda;
    }

    @Override
    public String toString() {
        return super.getNombre() +
                ", eliminado por " + eliminadoPor.getNombre() +
                " en " + enRonda + '\n';
    }
}
