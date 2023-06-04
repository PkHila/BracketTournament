public class Main {
    public static void main(String[] args) {

        //Test de funcionamiento básico
        Competidor competidorA = new Competidor("The Beatles");
        Competidor competidorB = new Competidor("The Rolling Stones");
        Enfrentamiento testEnfrentamiento = new Enfrentamiento(competidorA, competidorB);
        System.out.println(testEnfrentamiento);
        Competidor ganador = testEnfrentamiento.votar(1);
        System.out.println("\nGanador: " + ganador);
    }
}