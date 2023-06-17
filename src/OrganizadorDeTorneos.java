import java.util.ArrayList;
import java.util.HashMap;

public class OrganizadorDeTorneos {
    private HashMap<String, PlantillaCompetidores> plantillas;

    public OrganizadorDeTorneos() {
        plantillas = new HashMap<>();
    }

    public PlantillaCompetidores crearPlantilla(String nombre, String categoria) {
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

    private ArrayList<Enfrentamiento> crearRonda(int cantEnfrentamientos, ArrayList<Competidor> competidoresDeRonda){
        ArrayList<Enfrentamiento> ronda = new ArrayList<>();
        int contEnfrentamientos = 0;
        int contCompetidores = 0;
        while(contEnfrentamientos < competidoresDeRonda.size()/2){
            Competidor competidor1 = competidoresDeRonda.get(contCompetidores);
            contCompetidores++;
            Competidor competidor2 = competidoresDeRonda.get(contCompetidores);
            contCompetidores++;

            Enfrentamiento nuevoEnfrentamiento = new Enfrentamiento(competidor1,competidor2);
            contEnfrentamientos++;
            ronda.add(nuevoEnfrentamiento);
        }
        return ronda;
    }

    public void jugarTorneo(PlantillaCompetidores plantilla) {
       /* ArrayList<ArrayList<Enfrentamiento>> Rondas = new ArrayList<>();
        int cantidadRondas = calcularCantidadDeRondas(torneo.getCantElementos());

        for (int i = 0; i < cantidadRondas; i++) {
            for (int i = 0; i < ; i++){

            }
        }*/
    }
}
