package Modelo.Excepciones;

import Modelo.Categoria;

/**
 * Se espera una excepcion de este tipo cuando una categoria no esta dentro de la enumeracion
 */
public class CategoriaInvalidaException extends Exception{
    Categoria categoriaRecibida;

    /**
     * Construye una excepcion de categoria invalida con el mensaje especificado y adjunta la categoria recibida
     * @param message
     * @param categoriaRecibida
     */
    public CategoriaInvalidaException(String message, Categoria categoriaRecibida) {
        super(message);
        this.categoriaRecibida = categoriaRecibida;
    }
}
