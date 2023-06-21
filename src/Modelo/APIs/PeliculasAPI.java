package Modelo.APIs;

import Modelo.Competidor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeliculasAPI extends API{
    @Override
    Competidor competidorDesdeJSON(JSONObject j) throws JSONException {
        Competidor nuevo = new Competidor();
        nuevo.setNombre(j.getString("Title"));
        nuevo.setInfo(j.getString("Year"));
        return nuevo;
    }

    @Override
    JSONArray arregloDesdeJSON(JSONObject j) throws JSONException {
        return j.getJSONArray("Search");
    }

    @Override
    String construirQuery(String terminoABuscar) {
        String apikey = "&apikey=e3c29b03";
        return "http://www.omdbapi.com/?s=" + terminoABuscar + apikey + "&type=movie";
    }
}
