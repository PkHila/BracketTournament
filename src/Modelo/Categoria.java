package Modelo;

public enum Categoria {

    ANIME("Anime"), MANGA("Manga"), MUSICA("Musica"), PELICULAS("Peliculas");

    private final String nombre;

    public String getNombre() {
        return nombre;
    }

    private Categoria(String nombre) {
        this.nombre = nombre;
    }
}
