package Modelo.Resultados;

import Modelo.Competidor;

/**
 * Esta clase contiene toda la información de un Competidor que fue eliminado así como en qué ronda quedó descalificado y ante qué Competidor
 */
public class Eliminado extends Competidor {
    private Competidor eliminadoPor;
    private String enRonda;

    /**
     * Construye una Instancia de un Competidor Eliminado con toda su información
     * @param nombre
     * @param info
     * @param eliminadoPor
     * @param enRonda
     */
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
