import Modelo.APIs.*;
import Modelo.Competidor;
import Modelo.Menu.Menu;
import Modelo.PlantillaCompetidores;
import Modelo.Resultados.ControladorArchivos.ControladorArchivos;
import Modelo.Resultados.Resultado;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ControladorArchivos<PlantillaCompetidores> controladorArchivoPlantillas = new ControladorArchivos<>();
        ArrayList<PlantillaCompetidores> plantillas = null;
        try {
            plantillas = controladorArchivoPlantillas.leer("plantillas.bin");
        } catch (IOException e) {
            plantillas = new ArrayList<>();
        }
        ControladorArchivos<Resultado> controladorArchivoResultados = new ControladorArchivos<>();
        ArrayList<Resultado> resultados = null;
        try {
            resultados = controladorArchivoResultados.leer("resultados.bin");
        } catch (IOException e) {
            resultados = new ArrayList<>();
        }

        Menu.principal(plantillas, resultados);
        try { // fixme: determinar si es mejor todo el en mismo try catch
            controladorArchivoPlantillas.grabar(plantillas, "plantillas.bin");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            controladorArchivoResultados.grabar(resultados, "resultados.bin");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        /*
        //Test de funcionamiento básico
        Competidor competidorA = new Competidor("The Beatles");
        Competidor competidorB = new Competidor("The Rolling Stones");
        Competidor competidorC = new Competidor("Led Zeppelin");
        Competidor competidorD = new Competidor("Queen");

        PlantillaCompetidores batallaDeLasBandas = new PlantillaCompetidores("Batalla de las Bandas", Categoria.PERSONALIZADA);
        batallaDeLasBandas.agregarCompetidor(competidorA);
        batallaDeLasBandas.agregarCompetidor(competidorB);
        batallaDeLasBandas.agregarCompetidor(competidorC);
        batallaDeLasBandas.agregarCompetidor(competidorD);

        OrganizadorDeTorneos sistema = new OrganizadorDeTorneos();
        try {
            Resultado resultado = sistema.jugarTorneo(batallaDeLasBandas);
            resultados.add(resultado);
            System.out.println(resultado);
            resultadoControladorArchivos.grabar(resultados, "resultados.bin");
            System.out.println(resultados);
        } catch (CompetidoresInsuficientesException | IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Categoria.PERSONALIZADA);*/

        /*SeriesAPI comp = new SeriesAPI();
        ArrayList<Competidor> competidores = comp.obtenerCompetidores("Teenage Mutant Ninja Turtles");
        System.out.println(competidores);*/
    }
}
