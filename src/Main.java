import Modelo.Categoria;
import Modelo.Competidor;
import Modelo.Excepciones.CompetidoresInsuficientesException;
import Modelo.Envoltorios.OrganizadorDeTorneos;
import Modelo.PlantillaCompetidores;

public class Main {
    public static void main(String[] args) {

        //Test de funcionamiento básico
        Competidor competidorA = new Competidor("The Beatles");
        Competidor competidorB = new Competidor("The Rolling Stones");
        Competidor competidorC = new Competidor("Led Zeppelin");
        Competidor competidorD = new Competidor("Queen");

        PlantillaCompetidores batallaDeLasBandas = new PlantillaCompetidores("Batalla de las Bandas", Categoria.MUSICA);
        batallaDeLasBandas.agregarCompetidor(competidorA);
        batallaDeLasBandas.agregarCompetidor(competidorB);
        batallaDeLasBandas.agregarCompetidor(competidorC);
        batallaDeLasBandas.agregarCompetidor(competidorD);

        OrganizadorDeTorneos sistema = new OrganizadorDeTorneos();
        try {
            Competidor ganador = sistema.jugarTorneo(batallaDeLasBandas);
            System.out.println("Ganador del torneo: "+ ganador);
        } catch (CompetidoresInsuficientesException e) {
            System.out.println(e.getMessage());
        }
    }
}