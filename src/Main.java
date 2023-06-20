import Modelo.Competidor;
import Modelo.Excepciones.CompetidoresInsuficientesException;
import Modelo.Envoltorios.OrganizadorDeTorneos;
import Modelo.PlantillaCompetidores;
import Modelo.Resultados.ControladorArchivos.ControladorArchivoResultados;
import Modelo.Resultados.Resultado;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Test de funcionamiento básico
        Competidor competidorA = new Competidor("The Beatles");
        Competidor competidorB = new Competidor("The Rolling Stones");
        Competidor competidorC = new Competidor("Led Zeppelin");
        Competidor competidorD = new Competidor("Queen");

        PlantillaCompetidores batallaDeLasBandas = new PlantillaCompetidores("Batalla de las Bandas", "Música");
        batallaDeLasBandas.agregarCompetidor(competidorA);
        batallaDeLasBandas.agregarCompetidor(competidorB);
        batallaDeLasBandas.agregarCompetidor(competidorC);
        batallaDeLasBandas.agregarCompetidor(competidorD);

        OrganizadorDeTorneos sistema = new OrganizadorDeTorneos();
        try {
            Resultado resultado = sistema.jugarTorneo(batallaDeLasBandas);
            System.out.println(resultado);
            ControladorArchivoResultados.grabar(resultado, "resultados.bin");
            ArrayList<Resultado> resultados = ControladorArchivoResultados.leer("resultados.bin");
            System.out.println(resultados);
        } catch (CompetidoresInsuficientesException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}