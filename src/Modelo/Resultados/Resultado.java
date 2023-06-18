package Modelo.Resultados;

import Modelo.Competidor;
import Modelo.Enfrentamiento;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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
        String listado = "";
        Iterator<Map.Entry<Competidor,Competidor>> it = perdedores.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Competidor,Competidor> entry = it.next();
            listado += entry.getKey().getNombre() + " ante " + entry.getValue().getNombre() + " en ronda x" + "\n";
        }
        return listado;
    }
}
