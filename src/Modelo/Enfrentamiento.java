package Modelo;

import Modelo.Resultados.Eliminado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Enfrentamiento {

    private ArrayList<Competidor> competidores;

    public Enfrentamiento(Competidor competidor1, Competidor competidor2){
        competidores = new ArrayList<>();
        competidores.add(competidor1);
        competidores.add(competidor2);
    }

    public void votar(int votacion)
    {
        if(competidores.get(1).equals(votacion)) {
            Collections.reverse(competidores);
        }
    }

    public Competidor getGanador() {
        return competidores.get(0);
    }
    public Competidor getPerdedor() {
        return competidores.get(1);
    }
    public int getCantCompetidores(){
        return competidores.size();
    }


    @Override
    public String toString() {
        return "Enfrentamiento:\n" + competidores;
    }
}
