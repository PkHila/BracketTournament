package Modelo.Menu;

import Modelo.APIs.*;
import Modelo.Categoria;
import Modelo.Competidor;
import Modelo.Envoltorios.OrganizadorDeTorneos;
import Modelo.Excepciones.CategoriaInvalidaException;
import Modelo.Excepciones.CompetidoresInsuficientesException;
import Modelo.PlantillaCompetidores;
import Modelo.Resultados.Resultado;
import org.json.JSONException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static int nextInt() {
        return scanner.nextInt();
    }

    public static void principal(ArrayList<PlantillaCompetidores> plantillas, ArrayList<Resultado> resultados) {
        int eleccion = 0;
        OrganizadorDeTorneos sistema = new OrganizadorDeTorneos();
        sistema.init(plantillas, resultados); // todo traer data de archivos al mapa de plantillas y tal vez resultados que no tenemos coleccion
        do {
            System.out.println("1 jugar torneo 2 administrar plantillas");

            switch (eleccion) {
                case 1 -> menuJugarTorneo();
                case 2 -> menuAdministrarPlantillas();
                default -> System.out.println("default"); // todo: cambiar esto
            }
        } while (eleccion != 0);
    }

    private static void menuAdministrarPlantillas() {
        int eleccion = 0;
        System.out.println("");
    }

    private static void menuJugarTorneo(OrganizadorDeTorneos sistema) {
        int eleccion = 0;
        PlantillaCompetidores plantilla = null;
        do {
            System.out.println("1 jugar torneo desde plantilla 2 crear nuevo torneo");

            switch (eleccion) {
                case 1 -> plantilla = menuJugarDesdePlantilla(sistema);
                case 2 -> plantilla = menuCrearNuevoTonreo(sistema);

            }
            if(plantilla != null) {
                try {
                    sistema.jugarTorneo(plantilla);
                } catch (CompetidoresInsuficientesException e) {
                    System.out.println("Competidores insuficientes: "); // todo desarrollar menu para elegir si jugar con menos o agregar
                }
            }

        } while (eleccion != 0);
    }

    private static PlantillaCompetidores menuJugarDesdePlantilla(OrganizadorDeTorneos sistema) {
        int eleccion = 0;
        Categoria categoria;
        PlantillaCompetidores plantilla = null;
        do {
            System.out.println("1 buscar por categoria 2 buscar por nombre 3 listar plantillas");
            switch (eleccion) {
                case 1: try{
                    categoria = menuElegirCategoria(sistema);
                }
                catch (CategoriaInvalidaException e){
                    System.out.println(e.getMessage());
                }
                    if(categoria != null) { //todo considerar hacer este bloque una nueva Excepción
                        plantilla = menuListarPlantillas(sistema, true, categoria);
                    }
                    else {
                        System.out.println("No se encontró la plantilla");
                    }
                    break;
                case 2 -> plantilla = sistema.buscarPlantilla(""); // todo scanner nextLine
                case 3 -> plantilla = menuListarPlantillas(sistema, false, null);
            }
        } while (eleccion != 0);
        return plantilla;
    }

    private static Categoria menuElegirCategoria(OrganizadorDeTorneos sistema) throws CategoriaInvalidaException {
        int eleccion = 0;
        Categoria categoria = null;
        do {
            System.out.println("1 Anime 2 Manga 3 Peliculas 4 Series 5 Juegos 6 Personalizada");
            switch (eleccion) {
                case 1 -> categoria = Categoria.ANIME;
                case 2 -> categoria = Categoria.MANGA;
                case 3 -> categoria = Categoria.PELICULAS;
                case 4 -> categoria = Categoria.SERIES;
                case 5 -> categoria = Categoria.JUEGOS;
                case 6 -> categoria = Categoria.PERSONALIZADA;
            }
        } while (eleccion != 0);
        if(categoria==null){
            throw new CategoriaInvalidaException("La categoria es invalida. Categoria: ", categoria);
        }
        return categoria;
    }

    private static PlantillaCompetidores menuListarPlantillas(OrganizadorDeTorneos sistema, boolean porCategoria, Categoria categoria) {
        PlantillaCompetidores plantilla = null;
        int eleccion = 0;
        int contador = 1;
        ArrayList<String> listaPlantillas;
        if(porCategoria) {
            listaPlantillas = sistema.listarPlantillas(categoria);
        }
        else {
            listaPlantillas = sistema.listarPlantillas();
        }

        for (String nombre : listaPlantillas) {
            System.out.println(contador + " " + nombre + "\n");
        }
        if(listaPlantillas.size() != 0) {
            System.out.println("elegir plantilla:");
            // todo scanner eleccion
            String nombre = listaPlantillas.get(eleccion-1);
            plantilla = sistema.buscarPlantilla(nombre);
        }
        else {
            System.out.println("No hay plantillas cargadas. Queres crear una?");
            // todo si o no
            plantilla = menuCrearNuevoTonreo();
        }
        return plantilla;
    }

    private static PlantillaCompetidores menuCrearNuevoTonreo(OrganizadorDeTorneos sistema) {
        String nombre = "";
        Categoria categoria = null;
        ArrayList<Competidor> busqueda;
        Competidor nuevoCompetidor;
        int eleccion = 1;
        while(eleccion == 1){
            try {
                categoria = menuElegirCategoria(sistema);
            } catch (CategoriaInvalidaException e) {
                throw new RuntimeException(e);      //todo tratamiento adecuado
            }
            PlantillaCompetidores plantilla = new PlantillaCompetidores(nombre,categoria);
            API miApi = accederAPI(categoria);
            if(miApi != null)
            {
                nuevoCompetidor = menuCrearNuevoCompetidor();
            }
            else{
                try {
                    busqueda = miApi.obtenerBusqueda("SCANNER"); //fixme reemplazar con Scanner
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                nuevoCompetidor = menuElegirResultadoBusqueda(busqueda);
            }
            plantilla.agregarCompetidor(nuevoCompetidor);
        }





    }

    private static Competidor menuElegirResultadoBusqueda(ArrayList<Competidor> resultados) {
        Competidor seleccion = null;
        int eleccion = 0;

        //todo aca va el scanner
        System.out.println("0 cancelar");
        seleccion = resultados.get(eleccion-1);
        return seleccion;
    }

    private static Competidor menuCrearNuevoCompetidor() {

        System.out.println("Ingrese nombre del competidor");
        String nombre = ""; //todo reemplazar por Scanner
        System.out.println("Ingrese una pieza de informacion sobre el competidor");
        String info = ""; //todo reemplazar por Scanner
        return new Competidor(nombre,info);
    }

    //TODO: completar opciones
    private static API accederAPI(Categoria categoria) throws CategoriaInvalidaException {
        return switch (categoria){
            case ANIME -> new AnimeAPI();
            case MANGA -> new MangaAPI();
            case PELICULAS -> new PeliculasAPI();
            case SERIES -> new SeriesAPI();
            case JUEGOS -> new JuegosAPI();
            case PERSONALIZADA -> throw new CategoriaInvalidaException("La categoria es invalida. Categoria: ", categoria);
        };
    }
}
