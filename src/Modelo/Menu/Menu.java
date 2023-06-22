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

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private OrganizadorDeTorneos sistema;

    public Menu() {
        scanner = new Scanner(System.in);
        sistema = new OrganizadorDeTorneos();
    }

    public void principal(ArrayList<PlantillaCompetidores> plantillas, ArrayList<Resultado> resultados) {
        int eleccion = 0;
        OrganizadorDeTorneos sistema = new OrganizadorDeTorneos();
        sistema.inicializarPlantillas(plantillas);
        do {
            System.out.println("1 jugar torneo 2 administrar plantillas");
            eleccion = Integer.parseInt(scanner.nextLine());
            switch (eleccion) {
                case 1 -> jugarTorneo();
                case 2 -> administrarPlantillas();
                default -> System.out.println("default"); // todo: cambiar esto
            }
        } while (eleccion != 0);
        scanner.close();
    }

    private void administrarPlantillas() {
        int eleccion = 0;
        System.out.println("");
    }

    private void jugarTorneo() {
        int eleccion = 0;
        PlantillaCompetidores plantilla = null;
        do {
            System.out.println("1 jugar torneo desde plantilla 2 crear nuevo torneo");

            switch (eleccion) {
                case 1 -> plantilla = jugarDesdePlantilla();
                case 2 -> plantilla = crearNuevoTorneo();

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

    private PlantillaCompetidores jugarDesdePlantilla() {
        int eleccion = 0;
        Categoria categoria = null;
        PlantillaCompetidores plantilla = null;
        do {
            System.out.println("1 buscar por categoria 2 buscar por nombre 3 listar plantillas");
            switch (eleccion) {
                case 1: try{
                    categoria = elegirCategoria();
                }
                catch (CategoriaInvalidaException e){
                    System.out.println(e.getMessage());
                }
                    if(categoria != null) { //todo considerar hacer este bloque una nueva Excepción
                        plantilla = listarPlantillas(true, categoria);
                    }
                    else {
                        System.out.println("No se encontró la plantilla");
                    }
                    break;
                case 2 -> plantilla = sistema.buscarPlantilla(""); // todo scanner nextLine
                case 3 -> plantilla = listarPlantillas(false, null);
            }
        } while (eleccion != 0);
        return plantilla;
    }

    private Categoria elegirCategoria() throws CategoriaInvalidaException {
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

    private PlantillaCompetidores listarPlantillas(boolean porCategoria, Categoria categoria) {
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
            plantilla = crearNuevoTorneo();
        }
        return plantilla;
    }

    private PlantillaCompetidores crearNuevoTorneo() {
        String nombre = "";
        Categoria categoria = null;
        ArrayList<Competidor> busqueda;
        Competidor nuevoCompetidor;
        int eleccion = 1;
        while(eleccion == 1){
            try {
                categoria = elegirCategoria();
            } catch (CategoriaInvalidaException e) {
                throw new RuntimeException(e);      //todo tratamiento adecuado
            }
            PlantillaCompetidores plantilla = new PlantillaCompetidores(nombre,categoria);
            API miApi = accederAPI(categoria);
            if(miApi != null)
            {
                nuevoCompetidor = crearNuevoCompetidor();
            }
            else{
                try {
                    busqueda = miApi.obtenerBusqueda("SCANNER"); //fixme reemplazar con Scanner
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                nuevoCompetidor = elegirResultadoBusqueda(busqueda);
            }
            plantilla.agregarCompetidor(nuevoCompetidor);
        }





    }

    private Competidor elegirResultadoBusqueda(ArrayList<Competidor> resultados) {
        Competidor seleccion = null;
        int eleccion = 0;

        //todo aca va el scanner
        System.out.println("0 cancelar");
        seleccion = resultados.get(eleccion-1);
        return seleccion;
    }

    private Competidor crearNuevoCompetidor() {

        System.out.println("Ingrese nombre del competidor");
        String nombre = ""; //todo reemplazar por Scanner
        System.out.println("Ingrese una pieza de informacion sobre el competidor");
        String info = ""; //todo reemplazar por Scanner
        return new Competidor(nombre,info);
    }

    //TODO: completar opciones
    private API accederAPI(Categoria categoria) throws CategoriaInvalidaException {
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
