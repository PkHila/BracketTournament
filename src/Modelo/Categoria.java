package Modelo;

public enum Categoria {

    ANIME("Anime"), MANGA("Manga"), PELICULAS("Peliculas"),
    SERIES("Series"), JUEGOS("Juegos"), PERSONALIZADA("Personalizada");

    private final String nombre;

    public String getNombre() {
        return nombre;
    }

    private Categoria(String nombre) {
        this.nombre = nombre;
    }
}
