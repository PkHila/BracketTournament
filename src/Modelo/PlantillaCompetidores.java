package Modelo;

import Modelo.Excepciones.CompetidoresInsuficientesException;

import java.io.Serializable;
import java.util.*;

/**
 * Esta clase contiene una colección de Competidores, un nombre descriptivo y una categoría. Es serializable.
 */
public class PlantillaCompetidores implements Serializable, IEntradaSalida<Competidor>{

    private HashSet<Competidor> listaCompetidores;
    private String nombre;
    private Categoria categoria;

    public PlantillaCompetidores(String nombre, Categoria categoria) {
        listaCompetidores = new HashSet<>();
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Agrega a un Competidor a la colección
     * @param nuevoCompetidor a agregar
     * @return true si el Competidor no se encontraba en la colección
     */
    public boolean agregar(Competidor nuevoCompetidor){
        return listaCompetidores.add(nuevoCompetidor);
    }

    /**
     * Elimina a un Competidor de la colección
     * @param competidor a eliminar
     * @return true si el Competidor se encontraba en la colección
     */
    public boolean eliminar(Competidor competidor) {
        return listaCompetidores.remove(competidor);
    }

    /**
     * Devuelve el tamaño de la colección
     * @return la cantidad de Competidores en la colección
     */
    public int getCantElementos() {
        return listaCompetidores.size();
    }


    public ArrayList<Competidor> copiarAlArray(int limite) throws CompetidoresInsuficientesException { //copia los elementos contenidos en el torneo a un Array con orden random
        ArrayList<Competidor> arrayCompetidores = new ArrayList<>();
        Iterator<Competidor> it = listaCompetidores.iterator();
        int cantidadDeCompetidores = 0;
        while (it.hasNext() && cantidadDeCompetidores < limite) {
                Competidor aux = it.next();
                Competidor competidor = new Competidor(aux.getNombre(), aux.getInfo());
                arrayCompetidores.add(competidor);
                cantidadDeCompetidores++;
        }
        if(!potenciaDeDos(arrayCompetidores.size())) {
            throw new CompetidoresInsuficientesException("La cantidad de competidores no es potencia de dos y no se puede jugar");
        }
        Collections.shuffle(arrayCompetidores);
        return arrayCompetidores;
    }

    public String listarCompetidores() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("- ");
        for (Competidor listaCompetidore : listaCompetidores) {
            stringBuilder.append(listaCompetidore.getNombre()).append("\n- ");
        }
        return stringBuilder.toString();
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
    } //todo mostrar los competidores uno por uno, sin usar el toString de HashSet
}
