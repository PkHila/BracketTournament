package Modelo.Envoltorios;

import Modelo.Categoria;
import Modelo.Competidor;
import Modelo.Enfrentamiento;
import Modelo.Excepciones.CompetidoresInsuficientesException;
import Modelo.PlantillaCompetidores;
import Modelo.Resultados.Resultado;

import java.io.Serializable;
import java.util.*;

/**
 * Clase envoltorio de la coleccion de plantillas de competidores
 * Tiene las reglas de negocio de las plantillas, permite su creacion, eliminacion y modificacion
 */
public class OrganizadorDeTorneos implements Serializable {
    private HashMap<String, PlantillaCompetidores> plantillas;

    /**
     * Construye una instancia con su coleccion vacia
     */
    public OrganizadorDeTorneos() {
        plantillas = new HashMap<>();
    }

    /**
     * Hace una busqueda por nombre en la coleccion de plantillas y la devuelve de ser encontrada
     * @param nombre
     * @return una plantilla dentro de la coleccion o null de no encontrarse
     */
    public PlantillaCompetidores buscarPlantilla(String nombre) {
        PlantillaCompetidores plantilla = null;
        if(plantillas.containsKey(nombre)) {
            plantilla = plantillas.get(nombre);
        }
        return plantilla;
    }

    /**
     * Construye un ArrayList con los nombres de las plantillas de la coleccion
     * @return un ArrayList con todos los nombres de las plantillas
     */
    public ArrayList<String> listarPlantillas(){
        ArrayList<String> nombresDePlantillas = new ArrayList<>();
        for (Map.Entry<String, PlantillaCompetidores> p : plantillas.entrySet()) {
            nombresDePlantillas.add(p.getKey());
        }
        return nombresDePlantillas;
    }

    /**
     * Construye un ArrayList con los nombres de las plantillas de la coleccion
     * filtrados por categoria
     * @param categoria
     * @return un ArrayList<String> con todos los nombres de las plantillas filtrados por categoria
     */
    public ArrayList<String> listarPlantillas(Categoria categoria){
        ArrayList<String> nombresDePlantillas = new ArrayList<>();
        for (Map.Entry<String, PlantillaCompetidores> p : plantillas.entrySet()) {
            if(p.getValue().getCategoria() == categoria){
                nombresDePlantillas.add(p.getKey());
            }
        }
        return nombresDePlantillas;
    }

    /**
     * Recibe una plantilla y la agrega si no es null y si no existe una plantilla con la misma clave en la coleccion
     * @param plantilla
     * @return false si plantilla == null o existe una plantilla con la misma clave en la coleccion, true en caso contrario
     */
    public boolean agregarPlantilla(PlantillaCompetidores plantilla) {
        boolean respuesta = false;
        if(plantilla != null && !plantillas.containsKey(plantilla.getNombre())) {
            plantillas.put(plantilla.getNombre(), plantilla);
            respuesta = true;
        }
        return respuesta;
    }

    /**
     * Elimina la plantilla provista de la coleccion si y solo si existe dentro de la coleccion
     * @param plantilla
     * @return true si existe una plantilla con la misma clave en la coleccion, false en caso contrario
     */
    public boolean eliminarPlantilla(PlantillaCompetidores plantilla) {
        boolean respuesta = false;
        if(plantilla != null && plantillas.containsKey(plantilla.getNombre())) {
            plantillas.remove(plantilla.getNombre());
            respuesta = true;
        }
        return respuesta;
    }

    /**
     * Elimina una plantilla en la coleccion si existe una plantilla asociada al nombre provisto
     * @param nombre
     * @return true si existe una plantilla asociada al nombre provisto, falso en caso contrario
     */
    public boolean eliminarPlantilla(String nombre) {
        boolean respuesta = false;
        if(plantillas.containsKey(nombre)) {
            plantillas.remove(nombre);
            respuesta = true;
        }
        return respuesta;
    }

    /**
     * Dada una cantidad de competidores, calcula una cantidad de rondas sabiendo que debe ser una potencia de dos
     * @param cantidadDeCompetidores
     * @return la cantidad de rondas de un torneo
     */
    private int calcularCantidadDeRondas(int cantidadDeCompetidores) {
        int cantRondas = 0;
        int potenciaDeDos = 2;
        while(cantidadDeCompetidores >= potenciaDeDos) {
            potenciaDeDos *= 2;
            cantRondas++;
        }
        return cantRondas;
    }

    /**
     * Instancia un ArrayList de Enfrentamientos desde un ArrayList de competidores
     * @param arregloCompetidores
     * @return una ronda de enfretamientos
     */
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

        return ronda;
    }

    /**
     * Provistos una plantilla, un Scanner y un limite se dispone a resolver los enfrentamientos que surgen de las llamadas sucesivas a crearRonda
     * @param competidores
     * @param scan
     * @param limite potencia de 2 para evitar la excepcion
     * @return  el resultado de haberse jugado un torneo
     * @throws CompetidoresInsuficientesException si la cantidad de competidores no es potencia de 2
     */
    public Resultado jugarTorneo(PlantillaCompetidores competidores, Scanner scan, int limite) throws CompetidoresInsuficientesException {
        Resultado resultado = new Resultado(competidores.getNombre(), competidores.getCategoria());
        Competidor ganador = null;

        //paso los competidores de la plantilla a un arreglo
        ArrayList<Competidor> arregloCompetidores;
        arregloCompetidores = competidores.copiarAlArray(limite);

        int cantidadRondas = calcularCantidadDeRondas(arregloCompetidores.size());
        resultado.setCantidadDeRondas(cantidadRondas);

        //Gran estructura de Rondas, contiene cada ronda
        ArrayList<ArrayList<Enfrentamiento>> rondas = new ArrayList<>();
        int voto = 0;

        for(int i = 0; i<cantidadRondas; i++){
            //Le agrego la primera ronda para testear
            rondas.add(crearRonda(arregloCompetidores));

            //Vacío el arreglo de Competidores
            arregloCompetidores.clear();

            for(Enfrentamiento enfrentamiento : rondas.get(i)){
                do{
                    System.out.println(enfrentamiento + "\nIngrese el numero de candidato a votar:");
                    voto = scan.nextInt();
                }while(voto < 1 || voto > enfrentamiento.getCantCompetidores());

                enfrentamiento.votar(voto);


                ganador = enfrentamiento.getGanador();
                for (int j = 1; j < enfrentamiento.getCantCompetidores(); j++) {
                    resultado.agregarEliminado(ganador, enfrentamiento.getPerdedor(j), i);
                }
                arregloCompetidores.add(ganador);
            }
        }
        resultado.setGanador(ganador);
        return resultado;
    }

    /**
     Provistos una plantilla y un Scanner se dispone a resolver los enfrentamientos que surgen de las llamadas sucesivas a crearRonda
     * @param competidores
     * @param scan
     * @return  el resultado de haberse jugado un torneo
     * @throws CompetidoresInsuficientesException si la cantidad de competidores no es potencia de 2
     */
    public Resultado jugarTorneo(PlantillaCompetidores competidores, Scanner scan) throws CompetidoresInsuficientesException {
        return jugarTorneo(competidores, scan, 16);
    }

    /**
     * Inicializa la colección de Plantillas desde un ArrayList si y solo si la colección está vacía
     * @param plantillasDeArchivo
     */
    public void inicializarPlantillas(ArrayList<PlantillaCompetidores> plantillasDeArchivo) {
        if (plantillas.size() == 0) {
            for (PlantillaCompetidores plantilla: plantillasDeArchivo) {
                plantillas.put(plantilla.getNombre(), plantilla);
            }
        }
    }

    /**
     * Pasa la colección a un ArrayList de plantillas. Los elementos que pasa son los mismos que contiene la colección
     * @param plantillas
     */
    public void pasarPlantillasAlArray(ArrayList<PlantillaCompetidores> plantillas) {
        Iterator<Map.Entry<String, PlantillaCompetidores>> it = this.plantillas.entrySet().iterator();
        while (it.hasNext()) {
            plantillas.add(it.next().getValue());
        }
    }
}
