package Modelo;

import Modelo.Excepciones.CompetidoresInsuficientesException;

import java.io.Serializable;
import java.util.*;

/**
 * Esta clase contiene una colección de Competidores, un nombre descriptivo y una categoría. Es serializable.
 */
public class PlantillaCompetidores implements Serializable{

    private HashSet<Competidor> listaCompetidores;
    private String nombre;
    private Categoria categoria;

    /**
     * Construye un Plantilla con su colección vacía. Nombre y categoría especificados.
     * @param nombre
     * @param categoria
     */
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
     * @param nuevoCompetidor
     * @return true si el Competidor no se encontraba en la colección
     */
    public boolean agregarCompetidor(Competidor nuevoCompetidor){
        return listaCompetidores.add(nuevoCompetidor);
    }

    /**
     * Elimina a un Competidor de la colección
     * @param competidor
     * @return true si el Competidor se encontraba en la colección
     */
    public boolean eliminarCompetidor(Competidor competidor) {
        return listaCompetidores.remove(competidor);
    }
    public boolean eliminarCompetidor(String nombre) {
        boolean respuesta = false;
        if(listaCompetidores.contains(nombre)) {
            listaCompetidores.remove(new Competidor(nombre));
            respuesta = true;
        }
        return respuesta;
    }

    /**
     * Devuelve el tamaño de la colección
     * @return la cantidad de Competidores en la colección
     */
    public int getCantElementos() {
        return listaCompetidores.size();
    }

    /**
     * Instancia un ArrayList con una cantidad potencia de 2 de Competidores en orden aleatorio y los devuelve
     * @param limite
     * @return un ArrayList de Competidores en orden aleatorio
     * @throws CompetidoresInsuficientesException si la colección no tiene una cantidad potencia de 2
     */
    public ArrayList<Competidor> copiarAlArray(int limite) throws CompetidoresInsuficientesException {
        if(!potenciaDeDos(listaCompetidores.size())) {
            throw new CompetidoresInsuficientesException("La cantidad de competidores no es potencia de dos y no se puede jugar");
        }
        ArrayList<Competidor> arrayCompetidores = new ArrayList<>();
        Iterator<Competidor> it = listaCompetidores.iterator();
        int cantidadDeCompetidores = 0;
        while (it.hasNext() && cantidadDeCompetidores < limite) {
            Competidor aux = it.next();
            Competidor competidor = new Competidor(aux.getNombre(), aux.getInfo());
            arrayCompetidores.add(competidor);
            cantidadDeCompetidores++;
        }
        Collections.shuffle(arrayCompetidores);
        return arrayCompetidores;
    }

    /**
     * Construye una lista de nombres de competidores
     * @return los nombres de los competidores como cadena de texto
     */
    public String listarCompetidores() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Competidor> it = listaCompetidores.iterator();
        stringBuilder.append(" - ");
        while (it.hasNext()) {
            stringBuilder.append(it.next().getNombre() + " - ");
        }
        return stringBuilder.toString();
    }

    /**
     * Calcula recursivamente si un numero es potencia de 2
     * @param numero
     * @return true si es potencia de 2, false en caso contrario
     */
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
