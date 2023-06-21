package Modelo.APIs;

import Modelo.Competidor;
import Modelo.ConsumoAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class API {

   public ArrayList<Competidor> obtenerCompetidores(String terminoABuscar){
      terminoABuscar = terminoABuscar.replaceAll(" ","%20");
      ArrayList<Competidor> topResultados = new ArrayList<>();
      String busqueda = construirQuery(terminoABuscar);
      try {
         JSONObject resultados = new JSONObject(ConsumoAPI.getInfo(busqueda));
         JSONArray arregloResultados = arregloDesdeJSON(resultados);
         for (int i = 0; i < arregloResultados.length() && i < 10; i++) {
            Competidor nuevo = competidorDesdeJSON(arregloResultados.getJSONObject(i));
            topResultados.add(nuevo);
         }

      } catch (JSONException e) { //todo: implementar tratamiento de excepción o throw
         throw new RuntimeException(e);
      }

      return topResultados;
   }
   abstract Competidor competidorDesdeJSON(JSONObject j) throws JSONException;
   abstract JSONArray arregloDesdeJSON(JSONObject j) throws JSONException;
   abstract String construirQuery(String terminoABuscar);
}
