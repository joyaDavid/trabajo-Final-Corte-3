
package persistence;

import exception.ArchivoInvalidoException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    public static void escribirLinea(String ruta, String linea, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, append))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
        }
    }

    public static List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
        return lineas;
    }

    public static void sobrescribirArchivo(String ruta, List<?> objetos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Object o : objetos) {
                bw.write(o.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobrescribir archivo: " + e.getMessage());
        }
    }

    public static void crearArchivoSiNoExiste(String ruta) {
        File archivo = new File(ruta);
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    public static boolean eliminarArchivo(String ruta) {
        return new File(ruta).delete();
    }

    public static boolean renombrarArchivo(String rutaVieja, String nuevoNombre) {
        File archivoViejo = new File(rutaVieja);
        File archivoNuevo = new File(archivoViejo.getParent(), nuevoNombre);
        return archivoViejo.renameTo(archivoNuevo);
    }
}