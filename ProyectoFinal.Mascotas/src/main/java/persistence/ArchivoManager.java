package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoManager {
    private final File archivo;

    public ArchivoManager(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        crearArchivoSiNoExiste();
    }
    
    private void crearArchivoSiNoExiste() {
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
    
    public void escribirLinea(String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public List<String> leerLineas() {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }

    public void sobrescribirArchivo(List<String> lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobrescribir el archivo: " + e.getMessage());
        }
    }

    public void limpiarArchivo() {
        sobrescribirArchivo(new ArrayList<>());
    }

    public boolean eliminarArchivo() {
        return archivo.delete();
    }

    public boolean renombrarArchivo(String nuevoNombre) {
        File nuevoArchivo = new File(archivo.getParent(), nuevoNombre);
        return archivo.renameTo(nuevoArchivo);
    }

    public long obtenerTamanioArchivo() {
        return archivo.length();
    }

    // ✅ Obtener ruta absoluta
    public String getRutaAbsoluta() {
        return archivo.getAbsolutePath();
    }

    public boolean esDirectorio() {
        return archivo.isDirectory();
    }

    public String getNombreArchivo() {
        return archivo.getName();
    }

    public static List<String> listarArchivos(String rutaDirectorio) {
        File dir = new File(rutaDirectorio);
        List<String> archivos = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            for (File archivo : dir.listFiles()) {
                archivos.add(archivo.getName());
            }
        }
        return archivos;
    }

    public String getRuta() {
        return archivo.getPath();
    }
}