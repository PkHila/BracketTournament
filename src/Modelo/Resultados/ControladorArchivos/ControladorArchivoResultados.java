package Modelo.Resultados.ControladorArchivos;

import Modelo.Resultados.Resultado;

import java.io.*;
import java.util.ArrayList;

public abstract class ControladorArchivoResultados {
    public static void grabar(Resultado resultado, String archivo) throws IOException {
        ArrayList<Resultado> resultados = null;
        try {
            resultados = leer(archivo);
        } catch (IOException e) { // siempre que se corrompa o no se pueda abrir se va a sobreescribir con lo que se esta intentando grabar
            resultados = new ArrayList<>();
            resultados.add(resultado);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(archivo);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        if (!resultados.contains(resultado)) {
            resultados.add(resultado);
        }
        for (Resultado r : resultados) {
            objectOutputStream.writeObject(r);
        }

        fileOutputStream.close();
        objectOutputStream.close();
    }

    public static ArrayList<Resultado> leer(String archivo) throws IOException {
        ArrayList<Resultado> resultados = new ArrayList<>();

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(archivo);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                Resultado resultado = (Resultado) objectInputStream.readObject();
                resultados.add(resultado);
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


        return resultados;
    }
}
