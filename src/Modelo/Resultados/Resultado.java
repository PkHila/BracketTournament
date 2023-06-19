package Modelo.Resultados;

import Modelo.Competidor;
import Modelo.Enfrentamiento;

import java.io.Serializable;
import java.util.*;

public class Resultado implements Serializable {
    private int cantidadDeRondas; // con esta informacion se puede inferir en que ronda perdio cada Competidor que no es el ganador
    private Competidor ganador;
    private LinkedHashMap<Competidor, Competidor> perdedores; // donde K es el perdedor y V es ante quien

    public Resultado() {
        this.cantidadDeRondas = 0;
        this.ganador = null;
        this.perdedores = new LinkedHashMap<>();
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

    public void agregar(Enfrentamiento enfrentamiento, Competidor ganador) {
        Competidor perdedor = null;
        if(!enfrentamiento.votar(0).equals(ganador)) {
            perdedor = enfrentamiento.votar(0);
        }
        else {
            perdedor = enfrentamiento.votar(1);
        }
        perdedores.put(perdedor, ganador);
    }

    @Override
    public String toString() {
        return "Resultados{" +
                "cantidadDeRondas=" + cantidadDeRondas +
                ", ganador=" + ganador +
                ", perdedores:\n" + listarPerdedores() +
                '}';
    }
    private String listarPerdedores() {
        Iterator<Map.Entry<Competidor,Competidor>> it = perdedores.entrySet().iterator();
        StringBuilder listado = new StringBuilder();
        Stack<Map.Entry<Competidor,Competidor>> entries = new Stack<>();
        int contador = 1;
        while (it.hasNext()) {
            entries.push(it.next());
        }
        while (!entries.isEmpty()) {
            Map.Entry<Competidor,Competidor> entry = entries.pop();
            String ronda = "";
            if(contador == 1) {
                ronda = "final";
            }
            listado.append(entry.getKey().getNombre() + " ante " + entry.getValue().getNombre() + " en " + queRondaEs(contador) + "\n");
            contador++;
        }
        return listado.toString();
    }
    private String queRondaEs(int contador) {
        String ronda = "";
        if(contador == 1) {
            ronda = "ronda final";
        } else if (contador < 4 && this.cantidadDeRondas > 2) {
            ronda = "ronda semi-final";
        } else if (contador < 8 && this.cantidadDeRondas > 3) {
            ronda = "cuartos de final";
        } else {
            ronda = "primera ronda";
        }
        return ronda;
    }
}
