package Modelo.Envoltorios;

import Modelo.*;
import Modelo.APIs.AnimeAPI;
import Modelo.APIs.MangaAPI;
import Modelo.Excepciones.CompetidoresInsuficientesException;
import Modelo.Resultados.Resultado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class OrganizadorDeTorneos implements Serializable {
    private HashMap<String, PlantillaCompetidores> plantillas;

    public OrganizadorDeTorneos() {
        plantillas = new HashMap<>();
    }

    public PlantillaCompetidores crearPlantilla(String nombre, Categoria categoria) {
        PlantillaCompetidores plantilla = new PlantillaCompetidores(nombre, categoria);
        return plantilla;
    }

    public PlantillaCompetidores buscarPlantilla(String nombre) throws Exception {
        PlantillaCompetidores plantilla = plantillas.get(nombre);
        if(plantilla == null) {
            throw new Exception("TorneoNotFound");
        }
        return plantilla;
    }
    public boolean agregarPlantilla(PlantillaCompetidores plantilla) {
        boolean respuesta = false;
        if(plantilla != null && !plantillas.containsKey(plantilla.getNombre())) {
            plantillas.put(plantilla.getNombre(), plantilla);
            respuesta = true;
        }
        /*
        try {
            buscarTorneo(torneo.getNombre());
        }
        catch (Exception e) {
            Torneo t = torneos.put(torneo.getNombre(), torneo);
            respuesta = true;
        }
        // esto es una manera organica de prevenir duplicados en un mapa, o hay mejores?
        */
        return respuesta;
    }

    public boolean eliminarPlantilla(PlantillaCompetidores plantilla) {
        boolean respuesta = false;
        if(plantilla != null && plantillas.containsKey(plantilla.getNombre())) {
            plantillas.remove(plantilla.getNombre());
            respuesta = true;
        }
        return respuesta;
    }
    public boolean eliminarPlantilla(String nombre) {
        boolean respuesta = false;
        if(plantillas.containsKey(nombre)) {
            plantillas.remove(nombre);
            respuesta = true;
        }
        return respuesta;
    }

    private int calcularCantidadDeRondas(int cantidadDeCompetidores) { // o cantCompetidores
        int cantRondas = 0;
        int potenciaDeDos = 2;
        while(cantidadDeCompetidores >= potenciaDeDos) {
            potenciaDeDos *= 2;
            cantRondas++;
        }
        return cantRondas;
    }

    private ArrayList<Enfrentamiento> crearRonda(ArrayList<Competidor> arregloCompetidores){

        ArrayList<Enfrentamiento>  ronda = new ArrayList<>();
        int cantEnfrentamientos = arregloCompetidores.size()/2; //la cant de enfrentamientos 1 vs 1 es la mitad de la cant de competidores;
        int i = 0; //índice de enfrentamientos
        int j = 0; //índice de competidores
        Enfrentamiento nuevoEnfrentamiento;
        while(i<cantEnfrentamientos){
            nuevoEnfrentamiento = new Enfrentamiento(arregloCompetidores.get(j), arregloCompetidores.get(j+1));
            ronda.add(nuevoEnfrentamiento);
            j = j + 2;
            i++;
        }

        // System.out.println("Ronda: \n" + ronda); //test

        return ronda;
    }

    public Resultado jugarTorneo(PlantillaCompetidores competidores) throws CompetidoresInsuficientesException {
        Resultado resultado = new Resultado(competidores.getNombre(), competidores.getCategoria());
        Competidor ganador = null;

        //paso los competidores de la plantilla a un arreglo
        ArrayList<Competidor> arregloCompetidores;
        arregloCompetidores = competidores.copiarAlArray();

        int cantidadRondas = calcularCantidadDeRondas(arregloCompetidores.size());
        resultado.setCantidadDeRondas(cantidadRondas);

        //Gran estructura de Rondas, contiene cada ronda
        ArrayList<ArrayList<Enfrentamiento>> rondas = new ArrayList<>();

        //----------------------------------------------------------------------------------//
        for(int i = 0; i<cantidadRondas; i++){
            //Le agrego la primera ronda para testear
            rondas.add(crearRonda(arregloCompetidores));

            //Vacío el arreglo de Competidores
            arregloCompetidores.clear();

            Random rand = new Random();//!!!Para testear. Remover luego!!!
            for(Enfrentamiento enfrentamiento : rondas.get(i)){
                enfrentamiento.votar(rand.nextInt(2));
                ganador = enfrentamiento.getGanador();
                resultado.agregarEliminado(ganador, enfrentamiento.getPerdedor(), i);
                arregloCompetidores.add(ganador);
            }
            resultado.setGanador(ganador);
        }

        return resultado;
    }

    //TODO: completar opciones
    /*private ArrayList<Competidor> obtenerCompetidores(String terminoABuscar, Categoria categoria){
        return switch (categoria){
            case ANIME -> new AnimeAPI().obtenerCompetidores(terminoABuscar);
            case MANGA -> new MangaAPI().obtenerCompetidores(terminoABuscar);
            case PELICULAS -> null;
        };
    }*/

}
