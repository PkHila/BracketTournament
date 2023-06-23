package Modelo;

public interface IEntradaSalida<T> {
    boolean agregar(T elemento);
    boolean eliminar(T elemento);
}
