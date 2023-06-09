package Modelo.APIs;

import Modelo.Competidor;
import Modelo.ConsumoAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MangaAPI extends API{

    @Override
    String construirQuery(String terminoABuscar) {
        return "https://api.jikan.moe/v4/manga?q=" + terminoABuscar + "&sfw&limit=5";
    }

    @Override
    public Competidor competidorDesdeJSON(JSONObject j) throws JSONException {
        Competidor nuevo = new Competidor();
        nuevo.setNombre(j.getString("title"));
        JSONObject published = j.getJSONObject("published");
        String [] from = published.getString("from").split("-");
        nuevo.setInfo(from[0]);
        return nuevo;
    }

    @Override
    JSONArray arregloDesdeJSON(JSONObject j) throws JSONException {
        return j.getJSONArray("data");
    }


}
