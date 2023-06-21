package Modelo.APIs;

import Modelo.Competidor;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class API {

   abstract ArrayList<Competidor> obtenerCompetidores(String terminoABuscar);
   abstract Competidor fromJSON(JSONObject j) throws JSONException;
}
