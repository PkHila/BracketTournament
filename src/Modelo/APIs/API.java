package Modelo.APIs;

import Modelo.Competidor;
import Modelo.ConsumoAPI;
import Modelo.Excepciones.QueryVaciaException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Clase abstracta padre de todas las APIs de este proyecto
 */
public abstract class API {

   /**
    * Realiza una query a la API correspondiente, construyendo la URL y acopiando los resultados
    * @param terminoABuscar un termino a buscar
    * @return un ArrayList de Competidores
    * @throws JSONException si ocurre algun problema en la conversion de JSON a Competidor
    * @throws QueryVaciaException si una query diera cero resultados
    */
   public ArrayList<Competidor> obtenerBusqueda(String terminoABuscar) throws JSONException, QueryVaciaException {
      terminoABuscar = terminoABuscar.replaceAll(" ","+");
      ArrayList<Competidor> topResultados = new ArrayList<>();
      String busqueda = construirQuery(terminoABuscar);

         JSONObject resultados = new JSONObject(ConsumoAPI.getInfo(busqueda));
         JSONArray arregloResultados = arregloDesdeJSON(resultados);
         if(arregloResultados.length() == 0){
            throw new QueryVaciaException("No se encontró ningún resultado para su busqueda");
         }
         for (int i = 0; i < arregloResultados.length() && i < 5; i++) {
            Competidor nuevo = competidorDesdeJSON(arregloResultados.getJSONObject(i));
            topResultados.add(nuevo);
         }

      return topResultados;
   }

   /**
    * Instancia un Competidor desde un Objeto JSON
    * @param j objeto Json
    * @return un competidor
    * @throws JSONException si ocurre algun problema en la conversion de JSON a Competidor
    */
   abstract Competidor competidorDesdeJSON(JSONObject j) throws JSONException;

   /**
    * Consume el arreglo de resultados provisto por una query
    * @param j objeto JSON
    * @return un Array JSON
    * @throws JSONException si la clave provista no fuese a mappear correctamente a un JSONArray
    */
   abstract JSONArray arregloDesdeJSON(JSONObject j) throws JSONException;

   /**
    * Construye la URL para hacer la query
    * @param terminoABuscar termino a buscar
    * @return la url 
    */
   abstract String construirQuery(String terminoABuscar);
}
