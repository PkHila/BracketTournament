import Modelo.Menu.Menu;
import Modelo.PlantillaCompetidores;
import Modelo.ControladorArchivos.ControladorArchivos;
import Modelo.Resultados.Resultado;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ControladorArchivos<PlantillaCompetidores> controladorArchivoPlantillas = new ControladorArchivos<>();
        ArrayList<PlantillaCompetidores> plantillas;
        try {
            plantillas = controladorArchivoPlantillas.leer("plantillas.bin");
        } catch (IOException e) {
            plantillas = new ArrayList<>();
        }
        ControladorArchivos<Resultado> controladorArchivoResultados = new ControladorArchivos<>();
        ArrayList<Resultado> resultados;
        try {
            resultados = controladorArchivoResultados.leer("resultados.bin");
        } catch (IOException e) {
            resultados = new ArrayList<>();
        }

        try {
            new Menu().principal(plantillas, resultados);
            controladorArchivoPlantillas.grabar(plantillas, "plantillas.bin");
            controladorArchivoResultados.grabar(resultados, "resultados.bin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
