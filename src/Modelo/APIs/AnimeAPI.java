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
        nuevo.setNombre(j.getString("title"));
        nuevo.setInfo(j.getString("synopsis"));
        //todo implementar o borrar estos atributos
        /*
        JSONObject aired = j.getJSONObject("aired");
        anime.setAnioDeLanzamiento(j.getInt("year");
        JSONObject images = j.getJSONObject("images");
        JSONObject jpg = images.getJSONObject("jpg");
        setImagen(jpg.getString("image_url"));*/
        return nuevo;
    }

    @Override
    JSONArray arregloDesdeJSON(JSONObject j) throws JSONException {
        return j.getJSONArray("data");
    }
}
