package Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Anime extends Competidor implements IconsumirJson{

    private String imagen;
    private int anioDeLanzamiento;

    public Anime(String nombre, String imagen, int anioDeLanzamiento) {
        super(nombre);
        this.imagen = imagen;
        this.anioDeLanzamiento = anioDeLanzamiento;
    }

    public String getNombre(){
        return super.getNombre();
    }

    public void setNombre(String nombre){
        super.setNombre(nombre);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getAnioDeLanzamiento() {
        return anioDeLanzamiento;
    }

    public void setAnioDeLanzamiento(int anioDeLanzamiento) {
        this.anioDeLanzamiento = anioDeLanzamiento;
    }

    @Override
    public void fromJSON(JSONObject j) throws JSONException {
        super.setNombre(j.getString("title"));
        JSONObject aired = j.getJSONObject("aired");
        String fechaCompleta = aired.getString("from");
        String[] s = fechaCompleta.split("-", 2); //Extrae solo el año de la fecha
        setAnioDeLanzamiento(Integer.parseInt(s[0]));
        JSONObject images = j.getJSONObject("images");
        JSONObject jpg = images.getJSONObject("jpg");
        setImagen(jpg.getString("image_url"));
    }
}
