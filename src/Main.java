import Modelo.APIs.*;
import Modelo.Competidor;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        ControladorArchivos<Resultado> resultadoControladorArchivos = new ControladorArchivos<>();
        ArrayList<Resultado> resultados = null;
        try {
            resultados = resultadoControladorArchivos.leer("resultados.bin");
        } catch (IOException e) {
            resultados = new ArrayList<>();
        }

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

        AnimeAPI comp = new AnimeAPI();
        ArrayList<Competidor> competidores = comp.obtenerCompetidores("One Piece");
        System.out.println(competidores);
    }
}