import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Test de funcionamiento básico
        Competidor competidorA = new Competidor("The Beatles");
        Competidor competidorB = new Competidor("The Rolling Stones");
        Competidor competidorC = new Competidor("Led Zeppelin");
        Competidor competidorD = new Competidor("Queen");
        /*Enfrentamiento testEnfrentamiento = new Enfrentamiento(competidorA, competidorB);
        System.out.println(testEnfrentamiento);
        Competidor ganador = testEnfrentamiento.votar(1);
        System.out.println("\nGanador: " + ganador);*/

        PlantillaCompetidores batallaDeLasBandas = new PlantillaCompetidores("Batalla de las Bandas", "Música");
        batallaDeLasBandas.agregarCompetidor(competidorA);
        batallaDeLasBandas.agregarCompetidor(competidorB);
        batallaDeLasBandas.agregarCompetidor(competidorC);
        batallaDeLasBandas.agregarCompetidor(competidorD);
        ArrayList<Competidor> competidores = new ArrayList<>();
        batallaDeLasBandas.copiarAlArray(competidores);
        System.out.println(competidores);


    }
}