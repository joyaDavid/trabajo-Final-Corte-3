package dao;

import dto.VeterinarioDTO;
import java.util.ArrayList;
import java.util.List;
import persistence.GestorPersistencia;

public class VeterinarioDAO {

    private final String RUTA = "data/propietario.dat";
    private final GestorPersistencia gest = GestorPersistencia.getInstance();

    public void guardar(VeterinarioDTO consulta) {
        gest.crearArchivoSiNoExiste(RUTA);
        List<VeterinarioDTO> lista = listar();
        lista.add(consulta);
        gest.guardarLista(RUTA, lista);

    }

    public List<VeterinarioDTO> listar() {
        List<VeterinarioDTO> lista = gest.cargarLista(RUTA);
        return lista != null ? lista : new ArrayList<>();
    }

    public void actualizar(int indice, VeterinarioDTO mascotaActualizada) {
        List<VeterinarioDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, mascotaActualizada);
            gest.guardarLista(RUTA, lista);
        }
    }

    public void eliminar(int indice) {
        List<VeterinarioDTO> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.remove(indice);
            gest.guardarLista(RUTA, lista);
        }
    }
}
