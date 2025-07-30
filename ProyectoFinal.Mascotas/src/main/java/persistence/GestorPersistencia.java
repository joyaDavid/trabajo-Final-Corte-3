
package persistence;
import java.io.Serializable;
import java.io.*;
import java.util.List;

public class GestorPersistencia {

    private static GestorPersistencia instancia;

    private GestorPersistencia() {}

    public static synchronized GestorPersistencia getInstance() {
        if (instancia == null) {
            instancia = new GestorPersistencia();
        }
        return instancia;
    }
    
    public <T extends Serializable> void guardarLista(String ruta, List<T> lista) {
        try {         
            File archivo = new File(ruta);
            archivo.getParentFile().mkdirs();
            
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
          }
        } catch (IOException e) {
            System.err.println("❌ Error al guardar datos: " + e.getMessage());
        }
    }

    public <T extends Serializable> List<T> cargarLista(String ruta) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al leer datos: " + e.getMessage());
        }
        return null;
     }
    public void crearArchivoSiNoExiste(String ruta) {
    File archivo = new File(ruta);
    try {
        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs();  // crea carpeta si no existe
            archivo.createNewFile();           // crea archivo si no existe
            System.out.println("📁 Archivo creado en: " + archivo.getAbsolutePath());
        }
    } catch (IOException e) {
        System.err.println("❌ Error al crear archivo: " + e.getMessage());
    }
}
}