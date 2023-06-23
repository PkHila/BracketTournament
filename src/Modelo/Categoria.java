package Modelo;

/**
 * Enumeración de categorías
 */
public enum Categoria {

    ANIME("Anime",1), MANGA("Manga",2), PELICULAS("Peliculas",3),
    SERIES("Series",4), JUEGOS("Juegos",5), PERSONALIZADA("Personalizada",6);

    private final String nombre;
    private final int id;

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    private Categoria(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }
}
