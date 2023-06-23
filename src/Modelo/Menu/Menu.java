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
        OrganizadorDeTorneos sistema = new OrganizadorDeTorneos(plantillas);
        plantillas.clear();
        do {
            System.out.println("1 jugar torneo 2 administrar torneos");
            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1 -> jugarTorneo(resultados);
                case 2 -> administrarTorneos();
                case 0 -> System.out.println("Cerrando...");
                default -> System.out.println("Opción invalida");
            }
        } while (eleccion != 0);
        sistema.pasarPlantillasAlArray(plantillas);
    }

    private void administrarTorneos() {
        int eleccion = 0;
        PlantillaCompetidores plantilla = null;
        do {
            System.out.println("1 crear torneo 2 borrar torneo 3 modificar torneo");
            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1 -> {
                    plantilla = crearNuevoTorneo();
                    sistema.agregarPlantilla(plantilla);
                }
                case 2 -> {
                    plantilla = elegirPlantilla();
                    borrarPlantilla(plantilla);
                }
                case 3 -> {
                    plantilla = elegirPlantilla();
                    modificarPlantilla(plantilla);
                }

            }
        } while (eleccion != 0);
    }

    private void modificarPlantilla(PlantillaCompetidores plantilla) {
        int eleccion = 0;
        do {
            System.out.println(plantilla);
            System.out.println("1 agregar competidor 2 eliminar competidor");
            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1 -> agregarCompetidor(plantilla);
                case 2 -> eliminarCompetidor(plantilla);
            }
        } while (eleccion != 0);
    }

    private void borrarPlantilla(PlantillaCompetidores plantilla) {
        int eleccion = 0;
        System.out.println(plantilla);
        System.out.println("Estas seguro que queres borrar este torneo?\n 1. Sí\n2. No");
        eleccion = scanner.nextInt();
        if (eleccion == 1) {
            sistema.eliminarPlantilla(plantilla);
            System.out.println("torneo borrado");
        } else {
            System.out.println("operacion cancelada, volviendo al menu");
        }
    }

    private void jugarTorneo(ArrayList<Resultado> resultados) {
        int eleccion = 0;
        PlantillaCompetidores plantilla = null;
        do {
            System.out.println("1 jugar torneo desde plantilla 2 crear nuevo torneo");
            eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1 -> plantilla = elegirPlantilla();
                case 2 -> plantilla = crearNuevoTorneo();

            }
            if(plantilla != null) {
                try {
                    Resultado resultado = sistema.jugarTorneo(plantilla, scanner);
                    resultados.add(resultado);
                } catch (CompetidoresInsuficientesException e) {
                    tratarCompetidoresInsuficientes(plantilla, scanner, resultados);
                }
            }

        } while (eleccion != 0);
    }

    private void tratarCompetidoresInsuficientes(PlantillaCompetidores plantilla, Scanner scanner, ArrayList<Resultado> resultados) {
        System.out.println("Competidores insuficientes: " + "actualmente " + plantilla.getCantElementos());
        int eleccion = 0;
        int cantidadParaJugar = plantilla.getCantElementos();
        do {
            System.out.println("1 jugar con menos competidores 2 agregar mas competidores");
            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1 -> {
                    while(cantidadParaJugar != 8 && cantidadParaJugar != 4) {
                        cantidadParaJugar--;
                    }
                }
                case 2 -> {
                    while (cantidadParaJugar != 8 && cantidadParaJugar != 16) {
                        agregarCompetidor(plantilla);
                        cantidadParaJugar++;
                    }
                }
            }
        } while (eleccion != 0 && eleccion != 1 && eleccion != 2);
        if(eleccion != 0) {
            try {
                Resultado resultado = sistema.jugarTorneo(plantilla, scanner, cantidadParaJugar);
                resultados.add(resultado);
            } catch (CompetidoresInsuficientesException e) {
                System.out.println("Algo malo ocurrio, esto no deberia ocurrir: " + e.getMessage());
            }
        }
    }

    private PlantillaCompetidores elegirPlantilla() {
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

    private Categoria getCategoria(int id){
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
        PlantillaCompetidores plantilla = null;
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
            eleccion = scanner.nextInt();
            if(eleccion == 1) {
                plantilla = crearNuevoTorneo();
            }
            
        }
        return plantilla;
    }

    private PlantillaCompetidores crearNuevoTorneo() {
        System.out.println("Crear nueva plantilla:\n");
        scanner.nextLine();
        System.out.println("Ingrese nombre de la plantilla");
        String nombre = scanner.nextLine();
        Categoria categoria;

        try {
            categoria = elegirCategoria();
        } catch (CategoriaInvalidaException e) {
            System.out.println("Categoria invalida, asignando Personalizada por defecto");
            categoria = Categoria.PERSONALIZADA;
        }
        PlantillaCompetidores plantilla = new PlantillaCompetidores(nombre,categoria);

        int eleccion = 1;
        while(eleccion == 1){
            agregarCompetidor(plantilla);
            System.out.println("Competidores actuales: " + plantilla.getCantElementos() + "\nDesea agregar otro competidor?\n1.Sí\n2.No");
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
        while(eleccion <= 0 || eleccion > resultados.size()){
            System.out.println("Ingrese el elemento que quiere agregar:");
            eleccion = scanner.nextInt();
        }

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

    private void agregarCompetidor(PlantillaCompetidores plantilla) {
        Competidor nuevoCompetidor;
        API miApi = accederAPI(plantilla.getCategoria());
        ArrayList<Competidor> busqueda;
        if(miApi == null)
        {
            nuevoCompetidor = crearCompetidorPersonalizado();
        }
        else{
            try {
                System.out.println("Presione enter para continuar...");
                scanner.nextLine();
                System.out.println("Buscar en " + plantilla.getCategoria() + ":");
                busqueda = miApi.obtenerBusqueda(scanner.nextLine());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            nuevoCompetidor = elegirResultadoBusqueda(busqueda);
        }
        plantilla.agregarCompetidor(nuevoCompetidor);
    }

    private void eliminarCompetidor(PlantillaCompetidores plantilla) {
        scanner.nextLine();
        System.out.println("escriba un nombre de la lista o cualquier otra cosa para cancelar");
        System.out.println(plantilla.listarCompetidores());
        if(plantilla.eliminarCompetidor(new Competidor(scanner.nextLine()))) {
            System.out.println("competidor eliminado");
        }
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
