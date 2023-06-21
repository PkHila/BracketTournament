package Modelo.APIs;
import Modelo.Competidor;
import Modelo.ConsumoAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnimeAPI extends API{

    public ArrayList<Competidor> obtenerCompetidores(String terminoABuscar){
        ArrayList<Competidor> topResultados = new ArrayList<>();
        StringBuilder url = new StringBuilder();
        url.append("https://api.jikan.moe/v4/anime?q=").append(terminoABuscar).append("&sfw&limit=3");
        try {
            JSONObject resultado = new JSONObject(ConsumoAPI.getInfo(url.toString()));
            JSONArray data = resultado.getJSONArray("data");
            for (int i = 0; i < 3; i++) {
                Competidor nuevoAnime = fromJSON(data.getJSONObject(i));
                topResultados.add(nuevoAnime);
            }

        } catch (JSONException e) { //todo: implementar tratamiento de excepción o throw
            throw new RuntimeException(e);
        }

        return topResultados;
    }

    public Competidor fromJSON(JSONObject j) throws JSONException {
        Competidor anime = new Competidor();
        anime.setNombre(j.getString("title"));
        anime.setInfo(j.getString("synopsis"));
        //todo: implementar o borrar estos atributos
        /*
        JSONObject aired = j.getJSONObject("aired");
        anime.setAnioDeLanzamiento(j.getInt("year");
        JSONObject images = j.getJSONObject("images");
        JSONObject jpg = images.getJSONObject("jpg");
        setImagen(jpg.getString("image_url"));*/
        return anime;
    }
}
