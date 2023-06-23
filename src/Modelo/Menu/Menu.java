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
            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1 -> jugarTorneo();
                case 2 -> administrarPlantillas();
                case 0 -> System.out.println("Cerrando...");
                default -> System.out.println("Opción invalida");
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
            eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1 -> plantilla = jugarDesdePlantilla();
                case 2 -> plantilla = crearNuevoTorneo();

            }
            if(plantilla != null) {
                try {
                    sistema.jugarTorneo(plantilla, scanner);
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
            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1 -> {
                    try {
                        categoria = elegirCategoria();
                    } catch (CategoriaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    if (categoria != null) {
                        plantilla = listarPlantillas(true, categoria);
                    } else {
                        System.out.println("No se encontró la plantilla");
                    }
                }
                case 2 -> {
                    System.out.println("Ingrese el nombre de la plantilla a buscar");
                    plantilla = sistema.buscarPlantilla(scanner.nextLine());
                }
                case 3 -> plantilla = listarPlantillas(false, null);
            }
        } while (eleccion != 0);
        return plantilla;
    }

    private Categoria elegirCategoria() throws CategoriaInvalidaException {
        int eleccion = 0;
        Categoria categoria = null;

        System.out.println("1 Anime 2 Manga 3 Peliculas 4 Series 5 Juegos 6 Personalizada");
        eleccion = scanner.nextInt();
        categoria = getCategoria(eleccion);


        if(categoria==null){
            throw new CategoriaInvalidaException("La categoria es invalida. Categoria: ", categoria);
        }
        return categoria;
    }

    public Categoria getCategoria(int id){
        return switch (id) {
            case 1 -> Categoria.ANIME;
            case 2 -> Categoria.MANGA;
            case 3 -> Categoria.PELICULAS;
            case 4 -> Categoria.SERIES;
            case 5 -> Categoria.JUEGOS;
            case 6 -> Categoria.PERSONALIZADA;
            default -> null;
        };
    }

    private PlantillaCompetidores listarPlantillas(boolean porCategoria, Categoria categoria) {
        PlantillaCompetidores plantilla;
        int eleccion = 0;
        ArrayList<String> listaPlantillas;
        if(porCategoria) {
            listaPlantillas = sistema.listarPlantillas(categoria);
        }
        else {
            listaPlantillas = sistema.listarPlantillas();
        }

        for (int i = 0; i < listaPlantillas.size(); i++) {
            System.out.println(i+1 + ": " + listaPlantillas.get(i) + "\n");
        }
        if(listaPlantillas.size() != 0) {
            System.out.println("elegir plantilla:");
            eleccion = scanner.nextInt();
            String nombre = listaPlantillas.get(eleccion-1);
            plantilla = sistema.buscarPlantilla(nombre);
        }
        else {
            System.out.println("No hay plantillas cargadas. Queres crear una?\n 1. Sí\n2. No");
            scanner.nextInt();
            plantilla = crearNuevoTorneo();
        }
        return plantilla;
    }

    private PlantillaCompetidores crearNuevoTorneo() { //fixme arreglar esta monstruosidad
        System.out.println("Crear nueva plantilla:\nPresione enter para continuar...");
        scanner.nextLine();
        System.out.println("Ingrese nombre de la plantilla");
        String nombre = scanner.nextLine();
        Categoria categoria;
        ArrayList<Competidor> busqueda;
        Competidor nuevoCompetidor;

        try {
            categoria = elegirCategoria();
        } catch (CategoriaInvalidaException e) {
            throw new RuntimeException(e);      //todo tratamiento adecuado
        }
        PlantillaCompetidores plantilla = new PlantillaCompetidores(nombre,categoria);
        API miApi = accederAPI(categoria);


        int eleccion = 1;
        while(eleccion == 1){
            if(miApi == null)
            {
                nuevoCompetidor = crearCompetidorPersonalizado();
            }
            else{
                try {
                    System.out.println("Presione enter para continuar...");
                    scanner.nextLine();
                    System.out.println("Buscar en " + categoria + ":");
                    busqueda = miApi.obtenerBusqueda(scanner.nextLine());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                nuevoCompetidor = elegirResultadoBusqueda(busqueda);
            }
            plantilla.agregarCompetidor(nuevoCompetidor);
            System.out.println("Desea agregar otro competidor?\n1.Sí\n2.No");
            eleccion = scanner.nextInt();
            while(eleccion != 1 && eleccion != 2){
                System.out.println("Opcion invalida");
                eleccion = scanner.nextInt();
            }
            if(eleccion == 2 && plantilla.getCantElementos() < 4){
                System.out.println("Necesita al menos 4 competidores para un torneo");
                eleccion = 1;
            }
        }

        return plantilla;



    }

    private Competidor elegirResultadoBusqueda(ArrayList<Competidor> resultados) {
        Competidor seleccion;
        int eleccion = 0;
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println("["+ (i+1) + "]" +resultados.get(i));
        }
        System.out.println("Ingrese el elemento que quiere agregar:");
        scanner.nextInt();
        //fixme validar que la eleccion no esté OOB

        seleccion = resultados.get(eleccion-1);
        return seleccion;
    }

    private Competidor crearCompetidorPersonalizado() {

        scanner.nextLine();
        System.out.println("Ingrese nombre del competidor");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese una pieza de informacion sobre el competidor");
        String info = scanner.nextLine();
        return new Competidor(nombre,info);
    }

    //TODO: completar opciones
    private API accederAPI(Categoria categoria){
        return switch (categoria){
            case ANIME -> new AnimeAPI();
            case MANGA -> new MangaAPI();
            case PELICULAS -> new PeliculasAPI();
            case SERIES -> new SeriesAPI();
            case JUEGOS -> new JuegosAPI();
            case PERSONALIZADA -> null; //fixme esto no lo chequeo en ningun lado AAAAAHHHH
        };
    }
}
