package Modelo.Resultados;

import Modelo.Competidor;
import Modelo.Enfrentamiento;

import java.io.Serializable;
import java.util.*;

public class Resultado implements Serializable {
    private int cantidadDeRondas; // con esta informacion se puede inferir en que ronda perdio cada Competidor que no es el ganador
    private Competidor ganador;
    private ArrayList<Eliminado> eliminados; // agregar informacion del torneo

    public Resultado() {
        this.cantidadDeRondas = 0;
        this.ganador = null;
        this.eliminados = new ArrayList<>();
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

    public void agregarEliminado(Competidor ganador, Competidor perdedor, int contador) {
        Eliminado eliminado = new Eliminado(perdedor.getNombre(), perdedor.getInfo(), ganador, queRondaEs(contador));
        eliminados.add(eliminado);
    }

    @Override
    public String toString() {
        return "Resultado del torneo: \n"+
                "Cantidad de rondas: " + cantidadDeRondas + "\n" +
                "Ganador: " + ganador.getNombre() + ", info: " + ganador.getInfo() + "\n" +
                "Eliminados:\n" + listarEliminados();
    }

    private String listarEliminados() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Eliminado eliminado : eliminados) {
            stringBuilder.append(eliminado.toString());
        }
        return stringBuilder.toString();
    }

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
