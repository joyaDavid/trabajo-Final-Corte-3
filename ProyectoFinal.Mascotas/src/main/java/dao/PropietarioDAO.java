
package dao;

import dto.PropietarioDTO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import persistence.GestorPersistencia;


public class PropietarioDAO {
     private final String RUTA = "data/mascotas.dat";
    private final GestorPersistencia gest = GestorPersistencia.getInstance();
   
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
            
        public void guardar(PropietarioDTO consulta) {
        List<PropietarioDTO> lista = listar();
        lista.add(consulta);
        gest.guardarLista(RUTA, lista);

    }

    public List<PropietarioDTO> listar() {
        List<PropietarioDTO> lista = gest.cargarLista(RUTA);
        return lista != null ? lista : new ArrayList<>();
    }

    public void eliminar(int indice) {
        List<PropietarioDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.remove(indice);
            gest.guardarLista(RUTA, lista);
        }
    }

    public void actualizar(int indice, PropietarioDTO mascotaActualizada) {
        List<PropietarioDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, mascotaActualizada);
            gest.guardarLista(RUTA, lista);
        }
    }  
 }


