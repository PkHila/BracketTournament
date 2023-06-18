package Modelo;

import Modelo.Excepciones.CompetidoresInsuficientesException;

import java.util.*;

public class PlantillaCompetidores {

    private HashSet<Competidor> listaCompetidores;
    private String nombre;
    private String categoria; //podría ser un ENUM tmb

    public PlantillaCompetidores(String nombre, String categoria) {
        listaCompetidores = new HashSet<>();
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean agregarCompetidor(Competidor nuevoCompetidor){
        return listaCompetidores.add(nuevoCompetidor);
    }

    public int getCantElementos() {
        return listaCompetidores.size();
    }


    public ArrayList<Competidor> copiarAlArray() throws CompetidoresInsuficientesException { //copia los elementos contenidos en el torneo a un Array con orden random
        if(!potenciaDeDos(listaCompetidores.size())) {
            throw new CompetidoresInsuficientesException("La cantidad de competidores no es potencia de dos y no se puede jugar");
        }
        ArrayList<Competidor> arrayCompetidores = new ArrayList<>();
        Iterator<Competidor> it = listaCompetidores.iterator();
        int cantidadDeCompetidores = 0;
        while (it.hasNext() && cantidadDeCompetidores < 16) {
            Competidor aux = it.next();
            Competidor competidor = new Competidor(aux.getNombre(), aux.getInfo());
            arrayCompetidores.add(competidor);
            cantidadDeCompetidores++;
        }
        Collections.shuffle(arrayCompetidores);
        return arrayCompetidores;
    }


    private static boolean potenciaDeDos(float numero) {
        if(numero == 1) {
            return true;
        } else if (numero >= 2) {
            return potenciaDeDos(numero / 2);
        } return false;
    }
    @Override
    public String toString() {
        return "Torneo: " + nombre + ", categoria:'" + categoria + "\n" + listaCompetidores;
    }
}
