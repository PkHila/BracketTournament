package Modelo.Resultados.ControladorArchivos;

import Modelo.Resultados.Resultado;

import java.io.*;
import java.util.ArrayList;

public class ControladorArchivos<T extends Serializable> {
    public void grabar(ArrayList<T> elementos, String archivo) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(archivo);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (T e : elementos) {
            objectOutputStream.writeObject(e);
        }

        fileOutputStream.close();
        objectOutputStream.close();
    }

    public ArrayList<T> leer(String archivo) throws IOException {
        ArrayList<T> elementos = new ArrayList<>();

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(archivo);
            objectInputStream = new ObjectInputStream(fileInputStream);
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
