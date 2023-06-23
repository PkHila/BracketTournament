package Modelo.Excepciones;

/**
 * Se espera una excepcion del tipo QueryVaciaException cuando resultara vacio el retorno de una query
 */
public class QueryVaciaException extends Exception{
    /**
     * Construye una excepcion del tipo query vacia con un mensaje descriptivo
     * @param message mensaje de error
     */
    public QueryVaciaException(String message) {
        super(message);
    }
}
