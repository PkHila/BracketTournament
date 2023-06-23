package Modelo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Esta clase contiene una colección de competidores y ofrece funcionalidad para votar. Se entiende que el primer elemento es el ganador de un Enfrentamiento.
 */
public class Enfrentamiento {

    private ArrayList<Competidor> competidores;

    /**
     * Se intancia un Enfrentamiento y se agregan a la colección los Competidores especificados
     * @param competidor1 cualquier competidor
     * @param competidor2 cualquier competidor
     */
    public Enfrentamiento(Competidor competidor1, Competidor competidor2){
        competidores = new ArrayList<>();
        competidores.add(competidor1);
        competidores.add(competidor2);
    }

    /**
     * Siendo validado de antemano el parámetro, se hace el intercambio entre el primer elemento y el que está en la posición especificada.
     * @param votacion un numero verificado dentro de los limites de la coleccion
     */
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
