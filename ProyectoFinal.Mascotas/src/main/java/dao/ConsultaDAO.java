
package dao;

import dto.ConsultaDTO;
import java.util.ArrayList;
import java.util.List;
import persistence.GestorPersistencia;

public class ConsultaDAO {
      
    private final String RUTA = "data/consulta.dat";
    private final GestorPersistencia gest = GestorPersistencia.getInstance();
        
    public void guardar(ConsultaDTO consulta) {
        List<ConsultaDTO> lista = listar();
        lista.add(consulta);
        gest.guardarLista(RUTA, lista);

    }

    public List<ConsultaDTO> listar() {
        List<ConsultaDTO> lista = gest.cargarLista(RUTA);
        return lista != null ? lista : new ArrayList<>();
    }

    public void eliminar(int indice) {
        List<ConsultaDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.remove(indice);
            gest.guardarLista(RUTA, lista);
        }
    }

    public void actualizar(int indice, ConsultaDTO mascotaActualizada) {
        List<ConsultaDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, mascotaActualizada);
            gest.guardarLista(RUTA, lista);
        }
    }
    
    public void limpiar() {
    gest.guardarLista(RUTA, new ArrayList<>());
    System.out.println("✔ Lista de consultas vaciada correctamente.");
   }
}
