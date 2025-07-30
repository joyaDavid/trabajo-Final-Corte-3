
package dao;

import dto.MascotaDTO;
import java.io.File;
import persistence.GestorPersistencia;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
   
    private final String RUTA = "data/mascotas.dat";
    private final GestorPersistencia gestor = GestorPersistencia.getInstance();
    
     public MascotaDAO() {
        System.out.println("🧭 Ruta absoluta: " + new File(RUTA).getAbsolutePath());
        gestor.crearArchivoSiNoExiste(RUTA);
    }
     
     public void guardar(MascotaDTO mascota) { 
        List<MascotaDTO> lista = listar();
        lista.add(mascota);
        gestor.guardarLista(RUTA, lista);
    }
    public List<MascotaDTO> listar() {
    List<MascotaDTO> lista = gestor.cargarLista(RUTA);
    return lista != null ? lista : new ArrayList<>();
}
    
    public MascotaDTO buscarPorNombre(String nombre) {
    List<MascotaDTO> mascotas = cargarMascotas();
    for (MascotaDTO m : mascotas) {
        if (m.getNombre().equalsIgnoreCase(nombre)) {
            return m;
        }
    }
    return null;
}

    public void eliminar(int indice) {
        List<MascotaDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.remove(indice);
            gestor.guardarLista(RUTA, lista);
        }
    }

    public void actualizar(int indice, MascotaDTO mascotaActualizada) {
        List<MascotaDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, mascotaActualizada);
            gestor.guardarLista(RUTA, lista);
        }
    }
    public void limpiarTodo() {
    gestor.guardarLista(RUTA, new ArrayList<>()); 
   }
    
    public List<MascotaDTO> cargarMascotas() {
        return GestorPersistencia.getInstance().cargarLista(RUTA);
    }
}


