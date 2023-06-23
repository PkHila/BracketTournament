package Modelo.APIs;
import Modelo.Competidor;
import Modelo.ConsumoAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnimeAPI extends API{



    public String construirQuery(String terminoABuscar){
        return "https://api.jikan.moe/v4/anime?q=" + terminoABuscar + "&sfw&limit=5";
    }

    public Competidor competidorDesdeJSON(JSONObject j) throws JSONException {
        Competidor nuevo = new Competidor();
        nuevo.setNombre(j.getString("title_english"));
        int year = j.getInt("year");
        nuevo.setInfo(String.valueOf(year));
        return nuevo;
    }

    @Override
    JSONArray arregloDesdeJSON(JSONObject j) throws JSONException {
        return j.getJSONArray("data");
    }
}
