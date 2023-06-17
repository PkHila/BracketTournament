import java.util.*;

public class PlantillaCompetidores {

    private HashSet<Competidor> listaCompetidores;
    private int cantElementos;

    private String nombre;
    private String categoria; //podría ser un ENUM tmb

    public PlantillaCompetidores(String nombre, String categoria) {
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

    public ArrayList<Competidor> copiarAlArray(){ //copia los elementos contenidos en el torneo a un Array con orden random
        // se podria llamar getCompetidores() y podria retornarlo, eso es problema de eleccion de diseño
        ArrayList<Competidor> arrayCompetidores = new ArrayList<>();
        Iterator<Competidor> it = listaCompetidores.iterator();
        while (it.hasNext()) { // tal vez && i < 16
            Competidor aux = it.next();
            Competidor competidor = new Competidor(aux.getNombre(), aux.getInfo());
            arrayCompetidores.add(competidor);
        }
        Collections.shuffle(arrayCompetidores);
        return arrayCompetidores;
    }

    @Override
    public String toString() {
        return "Torneo: " + nombre + ", categoria:'" + categoria + "\n" + listaCompetidores;
    }
}
