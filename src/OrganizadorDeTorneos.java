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
        /*while(cantidadDeCompetidores > 1) { // puede haber un problema con el descarte de restos sucesivos?
            cantidadDeCompetidores /= 2;
            cantRondas++;
        }*/
        int potenciaDeDos = 2;
        while(cantidadDeCompetidores <= potenciaDeDos) { // esto es mejor?
            potenciaDeDos *= 2;
            cantRondas++;
        }
        return cantRondas;
    }

    public void jugarTorneo(String nombre) {
        /** magia **/
    }
}
