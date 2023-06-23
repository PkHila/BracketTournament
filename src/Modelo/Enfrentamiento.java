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
        Collections.swap(competidores,0,votacion-1);
    }

    public Competidor getGanador() {
        return competidores.get(0);
    }
    public Competidor getPerdedor(int i) {
        return competidores.get(i);
    }
    public int getCantCompetidores(){
        return competidores.size();
    }


    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < competidores.size(); i++) {
            s = s.concat("[" + (i+1) + "]" + competidores.get(i).getNombre());
            if(i != competidores.size()-1){
                s = s.concat(" VS ");
            }
        }
        return s;
    }
}
