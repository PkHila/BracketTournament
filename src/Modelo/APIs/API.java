package Modelo.APIs;

import Modelo.Competidor;
import Modelo.ConsumoAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class API {

   public ArrayList<Competidor> obtenerBusqueda(String terminoABuscar) throws JSONException{
      terminoABuscar = terminoABuscar.replaceAll(" ","+");
      ArrayList<Competidor> topResultados = new ArrayList<>();
      String busqueda = construirQuery(terminoABuscar);

         JSONObject resultados = new JSONObject(ConsumoAPI.getInfo(busqueda));
         JSONArray arregloResultados = arregloDesdeJSON(resultados);
         for (int i = 0; i < arregloResultados.length() && i < 10; i++) {
            Competidor nuevo = competidorDesdeJSON(arregloResultados.getJSONObject(i));
            topResultados.add(nuevo);
         }

      return topResultados;
   }
   abstract Competidor competidorDesdeJSON(JSONObject j) throws JSONException;
   abstract JSONArray arregloDesdeJSON(JSONObject j) throws JSONException;
   abstract String construirQuery(String terminoABuscar);
}
