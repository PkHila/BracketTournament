import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Torneo {

    private HashSet<Competidor> listaCompetidores;
    private int cantElementos;

    private String nombre;
    private String categoria; //podría ser un ENUM tmb

    public Torneo(String nombre, String categoria) {
        listaCompetidores = new HashSet<>();
        cantElementos = 0;
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
        return cantElementos;
    }

    public void copiarAlArray(ArrayList<Competidor> arrayCompetidores){ //copia los elementos contenidos en el torneo a un Array con orden random
        Iterator<Competidor> it = listaCompetidores.iterator();
        while (it.hasNext()) {
            Competidor aux = it.next();
            Competidor competidor = new Competidor(aux.getNombre(), aux.getInfo());
            arrayCompetidores.add(competidor);
        }
    }

    @Override
    public String toString() {
        return "Torneo: " + nombre + ", categoria:'" + categoria + "\n" + listaCompetidores;
    }
}
