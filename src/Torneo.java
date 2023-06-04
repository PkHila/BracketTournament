import java.util.HashSet;

public class Torneo {

    private HashSet<Competidor> listaCompetidores;
    private String nombre;
    private String categoria; //podría ser un ENUM tmb

    public Torneo(String nombre, String categoria) {
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

    @Override
    public String toString() {
        return "Torneo: " + nombre + ", categoria:'" + categoria;
    }
}
