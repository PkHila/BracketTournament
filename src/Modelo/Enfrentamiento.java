package Modelo;

import java.util.ArrayList;

public class Enfrentamiento {

    private ArrayList<Competidor> competidores;

    public Enfrentamiento(Competidor competidor1, Competidor competidor2){
        competidores = new ArrayList<>();
        competidores.add(competidor1);
        competidores.add(competidor2);
    }

    public Competidor votar(int votacion)
    {
        ///Falta IndexOutOfBoundsException
        return competidores.get(votacion);
    }

    public int getCantCompetidores(){
        return competidores.size();
    }


    @Override
    public String toString() {
        return "Modelo.Enfrentamiento:\n" + competidores;
    }
}
