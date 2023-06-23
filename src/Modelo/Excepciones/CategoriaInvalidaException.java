package Modelo.Excepciones;

import Modelo.Categoria;

public class CategoriaInvalidaException extends Exception{
    Categoria categoriaRecibida;
    public CategoriaInvalidaException(String message, Categoria categoriaRecibida) {
        super(message);
        this.categoriaRecibida = categoriaRecibida;
    }
}
