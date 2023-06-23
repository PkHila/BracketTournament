package Modelo.Resultados;

import Modelo.Categoria;
import Modelo.Competidor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase contiene la información de un Torneo ya jugado, nombre y categoría, así como el ganador, cuántas rondas se jugaron y todos los Competidores Eliminados
 */
public class Resultado implements Serializable {
    private int cantidadDeRondas; // con esta informacion se puede inferir en que ronda perdio cada Competidor que no es el ganador
    private Competidor ganador;
    private ArrayList<Eliminado> eliminados; // agregar informacion del torneo
    private String nombre;
    private Categoria categoria;

    /**
     * Instancia un Resultado con el nombre y la categoria a la que pertenecen y los demás campos inicializados
     * @param nombre nombre descriptivo
     * @param categoria categoria dentro de la enumeracion
     */
    public Resultado(String nombre, Categoria categoria) {
        this.cantidadDeRondas = 0;
        this.ganador = null;
        this.eliminados = new ArrayList<>();
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public int getCantidadDeRondas() {
        return cantidadDeRondas;
    }

    public void setCantidadDeRondas(int cantidadDeRondas) {
        this.cantidadDeRondas = cantidadDeRondas;
    }

    public Competidor getGanador() {
        return ganador;
    }

    public void setGanador(Competidor ganador) {
        this.ganador = ganador;
    }

    /**
     * Agrega un Competidor Eliminado a la colección
     * @param ganador el que descalifico al perdedor
     * @param perdedor el descalificado
     * @param contador un contador para verificar la ronda
     */
    public void agregarEliminado(Competidor ganador, Competidor perdedor, int contador) {
        Eliminado eliminado = new Eliminado(perdedor.getNombre(), perdedor.getInfo(), ganador, queRondaEs(contador));
        eliminados.add(eliminado);
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Resultado de " + nombre + ": \n" +
                "Categoria: " + categoria + "\n" +
                "Cantidad de rondas: " + cantidadDeRondas + "\n" +
                "Ganador: " + ganador.getNombre() + ", info: " + ganador.getInfo() + "\n" +
                "Eliminados:\n" + listarEliminados();
    }

    /**
     * Devuelve la lista de Eliminados como cadena de texto
     * @return la lista de Eliminados
     */
    private String listarEliminados() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Eliminado eliminado : eliminados) {
            stringBuilder.append(eliminado.toString());
        }
        return stringBuilder.toString();
    }

    /**
     * Dependiendo del contador y la cantidad de rondas devuelve un texto indicativo de qué ronda es
     * @param contador un contador para verificar ronda
     * @return qué ronda es como cadena de texto
     */
    private String queRondaEs(int contador) {
        String ronda = "";
        if(contador == this.cantidadDeRondas - 1) {
            ronda = "ronda final";
        } else if (contador == this.cantidadDeRondas - 2 && this.cantidadDeRondas > 2) {
            ronda = "ronda semi-final";
        } else if (contador == this.cantidadDeRondas - 3 && this.cantidadDeRondas > 3) {
            ronda = "cuartos de final";
        } else {
            ronda = "primera ronda";
        }
        return ronda;
    }
}
