package Modelo.Excepciones;

/**
 * Se espera una excepcion de este tipo cuando la cantidad de competidores no satisface las reglas de un torneo
 * por ejemplo que no sea potencia de 2
 */
public class CompetidoresInsuficientesException extends Exception{

    /**
     * Construye una excepcion del tipo competidores insuficientes con el mensaje especificado
     * @param message mensaje de error
     */
    public CompetidoresInsuficientesException(String message) {
        super(message);
    }
}
