package Modelo.APIs;

import Modelo.Competidor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JuegosAPI extends API{
    @Override
    Competidor competidorDesdeJSON(JSONObject j) throws JSONException {
        Competidor nuevo = new Competidor();
        nuevo.setNombre(j.getString("name"));
        String [] released = j.getString("released").split("-");
        nuevo.setInfo(released[0]);
        return nuevo;
    }

    @Override
    String construirQuery(String terminoABuscar) {
        String apikey = "key=91423ab8ff144f4688dcae07fcd20888";
        return "https://api.rawg.io/api/games?" + apikey + "&search=" + terminoABuscar;
    }

    @Override
    JSONArray arregloDesdeJSON(JSONObject j) throws JSONException {
        return j.getJSONArray("results");
    }
}
