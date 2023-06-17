import java.util.ArrayList;
import java.util.HashMap;

public class OrganizadorDeTorneos {
    private HashMap<String, Torneo> torneos;

    public OrganizadorDeTorneos() {
        torneos = new HashMap<>();
    }

    public Torneo crearTorneo(String nombre, String categoria) {
        Torneo torneo = new Torneo(nombre, categoria);
        return torneo;
    }

    public Torneo buscarTorneo(String nombre) throws Exception {
        Torneo torneo = torneos.get(nombre);
        if(torneo == null) {
            throw new Exception("TorneoNotFound");
        }
        return torneo;
    }
    public boolean agregarTorneo(Torneo torneo) {
        boolean respuesta = false;
        if(torneo != null && !torneos.containsKey(torneo.getNombre())) {
            torneos.put(torneo.getNombre(), torneo);
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

    public boolean eliminarTorneo(Torneo torneo) {
        boolean respuesta = false;
        if(torneo != null && torneos.containsKey(torneo.getNombre())) {
            torneos.remove(torneo.getNombre());
            respuesta = true;
        }
        return respuesta;
    }
    public boolean eliminarTorneo(String nombre) {
        boolean respuesta = false;
        if(torneos.containsKey(nombre)) {
            torneos.remove(nombre);
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

    public void jugarTorneo(Torneo torneo) {
       /* ArrayList<ArrayList<Enfrentamiento>> Rondas = new ArrayList<>();
        int cantidadRondas = calcularCantidadDeRondas(torneo.getCantElementos());

        for (int i = 0; i < cantidadRondas; i++) {
            for (int i = 0; i < ; i++){

            }
        }*/
    }
}
