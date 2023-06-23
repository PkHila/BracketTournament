package Modelo.ControladorArchivos;

import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase generica maneja archivos de objetos del tipo especificado
 * @param <T> una Clase que implemente Serializable
 */
public class ControladorArchivos<T extends Serializable> {
    /**
     * Recorre un ArrayList de Objetos del tipo especificado y los guarda en un archivo con el nombre enviado por parametro
     * @param elementos
     * @param archivo
     * @throws IOException
     */
    public void grabar(ArrayList<T> elementos, String archivo) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(archivo);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (T e : elementos) {
            objectOutputStream.writeObject(e);
        }

        fileOutputStream.close();
        objectOutputStream.close();
    }

    /**
     * Lee el archivo con el nombre enviado por parametro y devuelve un ArrayList con los Objetos que leyó
     * @param archivo
     * @return Coleccion de Objetos del tipo especificado
     * @throws IOException
     */
    public ArrayList<T> leer(String archivo) throws IOException {
        ArrayList<T> elementos = null;

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(archivo);
            objectInputStream = new ObjectInputStream(fileInputStream);
            elementos = new ArrayList<>();
            while (true) {
                T elemento = (T) objectInputStream.readObject();
                elementos.add(elemento);
            }
        } catch (EOFException e) {
            // all good
        } catch (ClassNotFoundException e) {
            throw new IOException("No es un archivo de resultados", e);
        }
        finally {
            if(fileInputStream != null) {
                fileInputStream.close();
            }
            if(objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return elementos;
    }
}
