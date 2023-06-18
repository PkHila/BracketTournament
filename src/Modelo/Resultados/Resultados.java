package Modelo.Resultados;

import Modelo.Competidor;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Resultados implements Serializable {
    private int cantidadDeRondas; // con esta informacion se puede inferir en que ronda perdio cada Competidor que no es el ganador
    private Competidor ganador;
    private transient LinkedHashMap<Competidor, Competidor> perdedores; // donde K es el perdedor y V es ante quien

    public Resultados() {
        this.cantidadDeRondas = 0;
        this.ganador = null;
        this.perdedores = new LinkedHashMap<>();
    }
}
